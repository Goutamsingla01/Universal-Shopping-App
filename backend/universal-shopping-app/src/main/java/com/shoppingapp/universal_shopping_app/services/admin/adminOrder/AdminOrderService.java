package com.shoppingapp.universal_shopping_app.services.admin.adminOrder;

import com.shoppingapp.universal_shopping_app.dto.OrderDto;
import com.shoppingapp.universal_shopping_app.dto.OrderedProductsResponseDto;

import java.util.List;

public interface AdminOrderService {

    List<OrderDto> getAllPlacedOrders();

    OrderDto changeOrderStatus(Long orderId, String status);

    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);
}
