package com.shoppingapp.universal_shopping_app.dto;

import com.shoppingapp.universal_shopping_app.enums.OrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class OrderedProductsResponseDto {

    private List<ProductDto> productDtoList;

    private Long orderAmount;

    private Long discount;

    private Long totalAmount;

    private OrderStatus orderStatus;

}
