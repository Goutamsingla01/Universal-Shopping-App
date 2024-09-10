package com.shoppingapp.universal_shopping_app.dto;

import lombok.Data;

import java.time.LocalDate;



@Data
public class CouponDto {

    private Long id;

    private String name;

    private String code;

    private Long discount;

    private LocalDate expirationDate;
}
