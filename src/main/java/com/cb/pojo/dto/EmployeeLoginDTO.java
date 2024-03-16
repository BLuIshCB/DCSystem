package com.cb.pojo.dto;


import lombok.*;

import java.io.Serializable;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//lombok注解

//员工登录时传递的数据模型
public class EmployeeLoginDTO implements Serializable {

    //("用户名")
    private String username;

    //("密码")
    private String password;

}
