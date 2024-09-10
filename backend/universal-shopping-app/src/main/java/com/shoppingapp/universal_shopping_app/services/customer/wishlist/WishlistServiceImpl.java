package com.shoppingapp.universal_shopping_app.services.customer.wishlist;

import com.shoppingapp.universal_shopping_app.dto.WishlistDto;
import com.shoppingapp.universal_shopping_app.entity.CartItems;
import com.shoppingapp.universal_shopping_app.entity.Product;
import com.shoppingapp.universal_shopping_app.entity.User;
import com.shoppingapp.universal_shopping_app.entity.Wishlist;
import com.shoppingapp.universal_shopping_app.repositry.ProductRepositry;
import com.shoppingapp.universal_shopping_app.repositry.UserRepositry;
import com.shoppingapp.universal_shopping_app.repositry.WishlistRepositry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WishlistServiceImpl implements WishlistService{

    private final WishlistRepositry wishlistRepositry;

    private final UserRepositry userRepositry;

    private final ProductRepositry productRepositry;

    public WishlistDto addProductToWishlist(WishlistDto wishlistDto){
        Optional<Wishlist> optionalWishlistProduct=wishlistRepositry.findByUserIdAndProductId(wishlistDto.getUserId(),wishlistDto.getProductId());
        if(optionalWishlistProduct.isPresent()){
            return null;
        }else {
            Optional<Product> optionalProduct = productRepositry.findById(wishlistDto.getProductId());
            Optional<User> optionalUser = userRepositry.findById(wishlistDto.getUserId());

            if (optionalProduct.isPresent() && optionalUser.isPresent()) {

                Wishlist wishlist = new Wishlist();
                wishlist.setProduct(optionalProduct.get());
                wishlist.setUser(optionalUser.get());

                return wishlistRepositry.save(wishlist).getWishlistDto();
            }
            return null;
        }
    }

    public List<WishlistDto> getWishlistByUserId(Long userId){

        return wishlistRepositry.findAllByUserId(userId).stream().map(Wishlist::getWishlistDto).collect(Collectors.toList());

    }

    public boolean removeProduct(WishlistDto wishlistDto){
        Optional<Wishlist> optionalWishlistProduct=wishlistRepositry.findByUserIdAndProductId(wishlistDto.getUserId(),wishlistDto.getProductId());
//
        if(optionalWishlistProduct.isPresent()){
            wishlistRepositry.delete(optionalWishlistProduct.get());
            return true;
        }
        return false;
    }

}
