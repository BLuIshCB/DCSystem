package com.cb.server_user.service;

import com.cb.common.utils.RandomCodeUtils;
import com.cb.common.result.Result;
import com.cb.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserService  {



    @Autowired
    private UserMapper userMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public static final String LOGIN_CODE_KEY = "login:code:";
    //验证码在redis里过期时间：2min
    public static final Long LOGIN_CODE_TTL = 2L;

    public Result sendCode(String phone) {
        // 1.校验手机号
//        if (RegexUtils.isPhoneInvalid(phone)) {
//            // 2.如果不符合，返回错误信息
//            return Result.fail("手机号格式错误！");
//        }
        // 3.符合，生成验证码
        String code = RandomCodeUtils.randomNumbers(6);

        // 4.保存验证码到 redis
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phone, code, LOGIN_CODE_TTL, TimeUnit.MINUTES);

        // 5.发送验证码
        log.info("发送短信验证码成功，验证码：{}", code);

        // 返回ok
        return Result.success("验证码已发送");
    }
}
