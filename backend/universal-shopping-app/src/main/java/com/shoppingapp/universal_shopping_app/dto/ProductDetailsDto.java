package com.shoppingapp.universal_shopping_app.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDetailsDto {
    private ProductDto productDto;

    private List<ReviewDto> reviewDtoList;
}
