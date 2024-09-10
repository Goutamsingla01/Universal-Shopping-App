package com.shoppingapp.universal_shopping_app.services.customer;

import com.shoppingapp.universal_shopping_app.dto.ProductDetailsDto;
import com.shoppingapp.universal_shopping_app.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {

    List<ProductDto> getAllProducts();

    ProductDetailsDto getProductDetailById(Long productId);
}
