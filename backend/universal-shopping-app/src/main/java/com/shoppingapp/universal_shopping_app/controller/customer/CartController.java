package com.shoppingapp.universal_shopping_app.controller.customer;

import com.shoppingapp.universal_shopping_app.dto.*;
import com.shoppingapp.universal_shopping_app.exceptions.ValidationException;
import com.shoppingapp.universal_shopping_app.services.customer.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCartDto){
        return cartService.addProductToCart(addProductInCartDto);
    }

    @GetMapping("/cart/{userId}")
    private ResponseEntity<?> getCartByUserId(@PathVariable Long userId){
        OrderDto orderDto= cartService.getCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @GetMapping("/coupon/{userId}/{code}")
    public ResponseEntity<?> applyCoupon(@PathVariable Long userId, @PathVariable String code){
        try{
            OrderDto orderDto=cartService.applyCoupon(userId, code);
            return ResponseEntity.ok(orderDto);
        } catch (ValidationException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/addition")
    public ResponseEntity<OrderDto> increaseProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseProductQuantity(addProductInCartDto));
    }

    @PostMapping("/deduction")
    public ResponseEntity<OrderDto> decreaseProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreaseProductQuantity(addProductInCartDto));
    }

    @PostMapping("/place-order")
    public ResponseEntity<OrderDto> placeOrder(@RequestBody PlaceOrderDto placeOrderDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrder(placeOrderDto));
    }

    @DeleteMapping("/cart")
    public ResponseEntity<Void> deleteProduct(@RequestBody RemoveProductInCartDto removeProductInCartDto){
        boolean deleted= cartService.removeProduct(removeProductInCartDto);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/myOrders/{userId}")
    public ResponseEntity<List<OrderDto>> getMyPlacedOrders(@PathVariable Long userId){
        return ResponseEntity.ok(cartService.getMyPlacedOrders(userId));
    }
}
