package com.shoppingapp.universal_shopping_app.dto;

import lombok.Data;

@Data
public class RemoveProductInCartDto {
    private Long userId;

    private Long productId;

    private Long quantity;
}
