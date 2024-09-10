package com.shoppingapp.universal_shopping_app.repositry;

import com.shoppingapp.universal_shopping_app.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepositry extends JpaRepository<Review, Long> {

    List<Review> findAllByProductId(Long productId);
}
