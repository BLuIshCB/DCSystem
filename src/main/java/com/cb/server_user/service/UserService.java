package com.cb.server_user.service;

import com.cb.common.constant.JwtClaimsConstant;
import com.cb.common.exception.NameRepeatException;
import com.cb.common.properties.JwtProperties;
import com.cb.common.result.Result;
import com.cb.common.utils.JwtUtil;
import com.cb.common.utils.RandomCodeUtils;
import com.cb.mapper.UserMapper;
import com.cb.pojo.dto.UserRegisterDTO;
import com.cb.pojo.entity.User;
import com.cb.pojo.vo.UserLoginVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private JwtProperties jwtProperties;

    public static final String LOGIN_CODE_KEY = "login:phone_";
    //验证码在redis里过期时间：1min
    public static final Long LOGIN_CODE_TTL = 1L;

    public Result register (UserRegisterDTO userRegisterDTO){
        String name = userRegisterDTO.getName();
        if (!cheackName(name)){
            throw new NameRepeatException("名字重复");
        }
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO,user);
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);
        return Result.success();
    }
    /*
    * true 表示名字可用
    * false不可用
    * */
    public boolean cheackName(String name) {
        User usr = userMapper.getByName(name);
        if( usr != null){
            return false;
        }
        return  true;
    }

    public Result sendCode(String phone) {
        // 1.校验手机号
//        if (RegexUtils.isPhoneInvalid(phone)) {
//            // 2.如果不符合，返回错误信息
//            return Result.fail("手机号格式错误！");
//        }
        //查询是否有此手机号用户
        User user = userMapper.getByPhone(phone);
        if (user == null) {
            return Result.error("该手机号没有注册");
        }
        //查询是否发过短信
        if ( stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone)!=null) {
            return Result.error("不要重复发送短信");
        }
        // 3.符合，生成验证码
        String code = RandomCodeUtils.randomNumbers(6);

        // 4.保存验证码到 redis
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phone, code, LOGIN_CODE_TTL, TimeUnit.MINUTES);

        // 5.发送验证码
        log.info("发送短信验证码成功，验证码：{}", code);

        // 返回ok
        return Result.success("验证码已发送");
    }

    public Result loginbyPhone(String code, String phone) {
        String rediscode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);

        if (rediscode == null) {
            return Result.error("验证码错误");
        }
        if (!rediscode.equals(code)) {
            return Result.error("验证码错误");
        }
        //查询
        User user = userMapper.getByPhone(phone);

        //为用户生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .name(user.getName())
                .authentication(token).build();
        log.info("登录成功");
        return Result.success(userLoginVO);
    }
}
