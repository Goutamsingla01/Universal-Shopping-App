package com.shoppingapp.universal_shopping_app.services.customer.review;


import com.shoppingapp.universal_shopping_app.dto.OrderedProductsResponseDto;
import com.shoppingapp.universal_shopping_app.dto.ProductDto;
import com.shoppingapp.universal_shopping_app.dto.ReviewDto;
import com.shoppingapp.universal_shopping_app.entity.*;
import com.shoppingapp.universal_shopping_app.repositry.OrderRepositry;
import com.shoppingapp.universal_shopping_app.repositry.ProductRepositry;
import com.shoppingapp.universal_shopping_app.repositry.ReviewRepositry;
import com.shoppingapp.universal_shopping_app.repositry.UserRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final OrderRepositry orderRepositry;

    private final ProductRepositry productRepositry;

    private final UserRepositry userRepositry;

    private final ReviewRepositry reviewRepositry;

    public OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId){

        Optional<Order> optionalOrder=orderRepositry.findById(orderId);

        OrderedProductsResponseDto orderedProductsResponseDto=new OrderedProductsResponseDto();

        if(optionalOrder.isPresent()){
            orderedProductsResponseDto.setOrderAmount(optionalOrder.get().getAmount());
            orderedProductsResponseDto.setOrderStatus(optionalOrder.get().getOrderStatus());
            orderedProductsResponseDto.setDiscount(optionalOrder.get().getDiscount());
            orderedProductsResponseDto.setTotalAmount(optionalOrder.get().getTotalAmount());

            List<ProductDto> productDtoList=new ArrayList<>();
            for(CartItems cartItems:optionalOrder.get().getCartItems()){
                ProductDto productDto=new ProductDto();

                productDto.setId(cartItems.getProduct().getId());
                productDto.setName(cartItems.getProduct().getName());
                productDto.setPrice(cartItems.getPrice());
                productDto.setQuantity(cartItems.getQuantity());
                productDto.setByteImg(cartItems.getProduct().getImg());
                productDtoList.add(productDto);

            }
            orderedProductsResponseDto.setProductDtoList(productDtoList);
        }

        return orderedProductsResponseDto;


    }

    public Boolean giveReview(ReviewDto reviewDto) throws IOException {
        Optional<Product> optionalProduct=productRepositry.findById(reviewDto.getProductId());
        Optional<User> optionalUser=userRepositry.findById(reviewDto.getUserId());

        if(optionalProduct.isPresent() && optionalUser.isPresent()){
            Review review=new Review();

            review.setRating(reviewDto.getRating());
            review.setDescription(reviewDto.getDescription());
            review.setUser(optionalUser.get());
            review.setProduct(optionalProduct.get());
            review.setImg(reviewDto.getImg().getBytes());

            reviewRepositry.save(review);
            return true;
        }
        return false;
    }
}
