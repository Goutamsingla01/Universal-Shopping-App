package com.shoppingapp.universal_shopping_app.services.admin.coupon;


import com.shoppingapp.universal_shopping_app.entity.Coupon;
import com.shoppingapp.universal_shopping_app.exceptions.ValidationException;
import com.shoppingapp.universal_shopping_app.repositry.CouponRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService{

    private final CouponRepositry couponRepositry;

    public Coupon createCoupon(Coupon coupon){
        if(couponRepositry.existsByCode(coupon.getCode())){
            throw new ValidationException("Coupon code already exists.");
        }
        return couponRepositry.save(coupon);
    }

    public List<Coupon> getAllCoupons(){
          return couponRepositry.findAll();
    }
}
