package com.cb.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShoppingCartDTO implements Serializable {

    private Long dishId;
    private Long flavorId;

}
