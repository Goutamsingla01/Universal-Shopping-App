package com.shoppingapp.universal_shopping_app.repositry;


import com.shoppingapp.universal_shopping_app.entity.Order;
import com.shoppingapp.universal_shopping_app.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepositry extends JpaRepository<Order, Long> {

    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);

    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);

    List<Order> findAllByUserIdAndOrderStatusIn(Long userId,List<OrderStatus> orderStatusList);

}
