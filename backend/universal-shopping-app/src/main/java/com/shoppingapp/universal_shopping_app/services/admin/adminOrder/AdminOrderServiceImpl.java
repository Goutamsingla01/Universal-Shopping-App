package com.shoppingapp.universal_shopping_app.services.admin.adminOrder;


import com.shoppingapp.universal_shopping_app.dto.OrderDto;
import com.shoppingapp.universal_shopping_app.dto.OrderedProductsResponseDto;
import com.shoppingapp.universal_shopping_app.dto.ProductDto;
import com.shoppingapp.universal_shopping_app.entity.CartItems;
import com.shoppingapp.universal_shopping_app.entity.Order;
import com.shoppingapp.universal_shopping_app.enums.OrderStatus;
import com.shoppingapp.universal_shopping_app.repositry.OrderRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService{

    private final OrderRepositry orderRepositry;

    public List<OrderDto> getAllPlacedOrders(){
        List<Order> orderList=orderRepositry.findAllByOrderStatusIn(
                List.of(OrderStatus.Placed, OrderStatus.Delivered, OrderStatus.Shipped)
        );

        return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
    }
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

    public OrderDto changeOrderStatus(Long orderId, String status){
        Optional<Order> optionalOrder= orderRepositry.findById(orderId);
        if(optionalOrder.isPresent()){
            Order order=optionalOrder.get();

            if(Objects.equals(status, "Shipped")){
                order.setOrderStatus(OrderStatus.Shipped);
            }else{
                order.setOrderStatus(OrderStatus.Delivered);
            }

            return orderRepositry.save(order).getOrderDto();
        }
        return null;
    }
}
