package com.cb.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    private static final long serialVersionUID = 1L;



    //姓名
    private String name;

    //手机号
    private String phone;

    //性别 0 女 1 男
    private String sex;


    //头像
    private String avatar;


}
