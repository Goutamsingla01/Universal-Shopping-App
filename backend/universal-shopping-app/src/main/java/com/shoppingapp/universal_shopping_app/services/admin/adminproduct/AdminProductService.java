package com.shoppingapp.universal_shopping_app.services.admin.adminproduct;

import com.shoppingapp.universal_shopping_app.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {

    ProductDto addProduct(ProductDto productDto) throws IOException;

    List<ProductDto> getAllProducts();

    boolean deleteProduct(Long id);

    ProductDto getProductById (Long productId);

    ProductDto updateProduct(Long productId,ProductDto productDto) throws IOException;
}
