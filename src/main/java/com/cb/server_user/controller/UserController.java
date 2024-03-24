package com.cb.server_user.controller;

import com.cb.common.constant.JwtClaimsConstant;
import com.cb.common.properties.JwtProperties;
import com.cb.common.result.Result;
import com.cb.common.utils.JwtUtil;
import com.cb.pojo.dto.UserLoginDTO;
import com.cb.pojo.entity.User;
import com.cb.pojo.vo.UserLoginVO;
import com.cb.server_user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/user")
@Api(tags = "C端用户相关接口")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;


//    @PostMapping("/login")
//    @ApiOperation("微信登录")
//    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
//        log.info("微信用户登录：{}",userLoginDTO.getCode());
//
//        //微信登录
//        User user = userService.wxLogin(userLoginDTO);
//
//        //为微信用户生成jwt令牌
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(JwtClaimsConstant.USER_ID,user.getId());
//        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
//
//        UserLoginVO userLoginVO = UserLoginVO.builder()
//                .id(user.getId())
//                .openid(user.getOpenid())
//                .token(token)
//                .build();
//        return Result.success(userLoginVO);
//    }

    @GetMapping("/login/code")
    public Result code(@RequestParam("phone") String phone){

        // 发送短信验证码并保存验证码
        return userService.sendCode(phone);
    }
//    @PostMapping("/login/phone")
//    public Result login_phone(@RequestParam("code") String code){
//
//        return userService.sendCode(phone);
//    }
}
