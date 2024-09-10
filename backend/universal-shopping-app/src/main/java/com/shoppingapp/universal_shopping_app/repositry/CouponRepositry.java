package com.shoppingapp.universal_shopping_app.repositry;


import com.shoppingapp.universal_shopping_app.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepositry extends JpaRepository<Coupon, Long> {

    boolean existsByCode(String code);

    Optional<Coupon> findByCode(String code);

}
