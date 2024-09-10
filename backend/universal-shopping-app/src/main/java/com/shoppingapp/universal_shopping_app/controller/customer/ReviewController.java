package com.shoppingapp.universal_shopping_app.controller.customer;


import com.shoppingapp.universal_shopping_app.dto.OrderedProductsResponseDto;
import com.shoppingapp.universal_shopping_app.dto.ReviewDto;
import com.shoppingapp.universal_shopping_app.services.customer.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/ordered-products/{orderId}")
    public ResponseEntity<OrderedProductsResponseDto> getOrderedProductsDetailsByOrderId(@PathVariable Long orderId){
        return ResponseEntity.ok(reviewService.getOrderedProductsDetailsByOrderId(orderId));
    }


    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@ModelAttribute ReviewDto reviewDto) throws IOException {
        System.out.println("hi");
        System.out.println("productId"+reviewDto.getProductId());
        System.out.println("UserId for review:"+reviewDto.getUserId());
        Boolean success= reviewService.giveReview(reviewDto);

        if(!success) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
