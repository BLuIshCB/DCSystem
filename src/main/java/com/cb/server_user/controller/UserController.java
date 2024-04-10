package com.cb.server_user.controller;

import com.cb.common.constant.JwtClaimsConstant;
import com.cb.common.properties.JwtProperties;
import com.cb.common.result.Result;
import com.cb.common.utils.JwtUtil;
import com.cb.pojo.dto.UserLoginDTO;
import com.cb.pojo.dto.UserRegisterDTO;
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
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/checkName")
    public Result checkName(String name){
        log.info("name{}",name);
        if (userService.cheackName(name)){
            return Result.success("名称可用");
        }else {
            return Result.error("名称不可用");
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDTO userRegisterDTO ){

        return userService.register(userRegisterDTO);
    }
    @GetMapping("/sendCode")
    public Result code(@RequestParam("phone") String phone){
        // 发送短信验证码并保存验证码
        if (phone == null){
            return Result.error("手机号不能为空");
        }
        return userService.sendCode(phone);
    }
    @PostMapping("/login/phone")
    public Result login_phone(@RequestBody UserLoginDTO userLoginDTO ){
        if (userLoginDTO.getPhone() == null){
            return Result.error("手机号不能为空");
        }
        if (userLoginDTO.getCode() == null){
            return Result.error("验证码不能为空");
        }
        return userService.loginbyPhone(userLoginDTO.getCode(),userLoginDTO.getPhone());
    }

}
