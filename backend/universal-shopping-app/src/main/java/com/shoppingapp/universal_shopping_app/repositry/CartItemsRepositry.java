package com.shoppingapp.universal_shopping_app.repositry;


import com.shoppingapp.universal_shopping_app.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemsRepositry extends JpaRepository<CartItems, Long> {

    Optional<CartItems> findByProductIdAndOrderIdAndUserId(Long productId, Long orderId, Long userId);

}
