package com.shoppingapp.universal_shopping_app.dto;


import com.shoppingapp.universal_shopping_app.entity.CartItems;
import com.shoppingapp.universal_shopping_app.entity.User;
import com.shoppingapp.universal_shopping_app.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    private Long id;

    private String orderDescription;

    private LocalDate date;

    private Long amount;

    private String address;

    private String payment;

    private OrderStatus orderStatus;

    private Long totalAmount;

    private Long discount;

    private UUID trackingId;


    private String userName;

    private List<CartItemsDto> cartItems;

    private String couponName;
}
