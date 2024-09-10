package com.shoppingapp.universal_shopping_app.services.admin.coupon;

import com.shoppingapp.universal_shopping_app.entity.Coupon;

import java.util.List;


public interface AdminCouponService {

    Coupon createCoupon(Coupon coupon);

    List<Coupon> getAllCoupons();
}
