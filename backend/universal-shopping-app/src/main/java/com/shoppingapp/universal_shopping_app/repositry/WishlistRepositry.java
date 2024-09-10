package com.shoppingapp.universal_shopping_app.repositry;

import com.shoppingapp.universal_shopping_app.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepositry extends JpaRepository<Wishlist,Long> {

    List<Wishlist> findAllByUserId(Long userId);

    Optional<Wishlist> findByUserIdAndProductId(Long userId, Long productId);
}
