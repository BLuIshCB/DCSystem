package com.cb.server_admin.controller;

import com.cb.common.constant.JwtClaimsConstant;
import com.cb.common.properties.JwtProperties;
import com.cb.common.result.Result;
import com.cb.common.utils.JwtUtil;
import com.cb.pojo.dto.EmployeeLoginDTO;
import com.cb.pojo.entity.Employee;
import com.cb.pojo.vo.EmployeeLoginVO;
import com.cb.server_admin.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工相关接口")
/*
* 员工相关接口
* */
public class loginController {
    @Autowired
    private EmployeeService employeeService;
    //jtw的设置文件
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    @ApiOperation(value = "员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();//claims是jwt中间的内容部分
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        

        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),//获取key
                jwtProperties.getAdminTtl(),//设置超时时间
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }
    @GetMapping ("/test")
    public Result<String> test(){

        return Result.success("test");
    }

}
