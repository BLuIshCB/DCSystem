package com.cb.pojo.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// "员工登录返回的数据格式"
public class EmployeeLoginVO implements Serializable {


    private Long id;


    private String userName;


    private String name;

 //("jwt令牌")
    private String token;

}
