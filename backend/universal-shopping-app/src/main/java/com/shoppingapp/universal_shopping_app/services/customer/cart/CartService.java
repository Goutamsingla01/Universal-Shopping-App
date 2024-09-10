package com.shoppingapp.universal_shopping_app.services.customer.cart;

import com.shoppingapp.universal_shopping_app.dto.AddProductInCartDto;
import com.shoppingapp.universal_shopping_app.dto.OrderDto;
import com.shoppingapp.universal_shopping_app.dto.PlaceOrderDto;
import com.shoppingapp.universal_shopping_app.dto.RemoveProductInCartDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {

    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);

    OrderDto getCartByUserId(Long userId);

    OrderDto applyCoupon(Long userId, String code);

    OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);

    OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);

    OrderDto placeOrder(PlaceOrderDto placeOrderDto);

    List<OrderDto> getMyPlacedOrders(Long userId);

    boolean removeProduct(RemoveProductInCartDto removeProductInCartDto);

}
