package com.shoppingapp.universal_shopping_app.services.customer.review;

import com.shoppingapp.universal_shopping_app.dto.OrderedProductsResponseDto;
import com.shoppingapp.universal_shopping_app.dto.ReviewDto;

import java.io.IOException;

public interface ReviewService {

    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);

    Boolean giveReview(ReviewDto reviewDto) throws IOException;
}
