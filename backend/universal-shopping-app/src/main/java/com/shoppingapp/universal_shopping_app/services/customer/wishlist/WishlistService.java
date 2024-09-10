package com.shoppingapp.universal_shopping_app.services.customer.wishlist;

import com.shoppingapp.universal_shopping_app.dto.WishlistDto;

import java.util.List;

public interface WishlistService {

    WishlistDto addProductToWishlist(WishlistDto wishlistDto);

    List<WishlistDto> getWishlistByUserId(Long userId);

    boolean removeProduct(WishlistDto wishlistDto);
}
