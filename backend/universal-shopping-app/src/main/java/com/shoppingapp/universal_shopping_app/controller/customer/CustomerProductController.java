package com.shoppingapp.universal_shopping_app.controller.customer;


import com.shoppingapp.universal_shopping_app.dto.ProductDetailsDto;
import com.shoppingapp.universal_shopping_app.dto.ProductDto;
import com.shoppingapp.universal_shopping_app.services.customer.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {

    private final CustomerProductService customerProductService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtos=customerProductService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProductDetailById(@PathVariable Long productId){
        ProductDetailsDto productDetailsDto=customerProductService.getProductDetailById(productId);

        if(productDetailsDto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDetailsDto);
    }
}
