package com.shoppingapp.universal_shopping_app.controller.customer;

import com.shoppingapp.universal_shopping_app.dto.WishlistDto;
import com.shoppingapp.universal_shopping_app.services.customer.wishlist.WishlistService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;


    @PostMapping("/wishlist")
    public ResponseEntity<?> addProductToWishlist(@RequestBody WishlistDto wishlistDto){
        System.out.println(wishlistDto.getProductId());
        System.out.println(wishlistDto.getUserId());

        WishlistDto postedWishlistDto=wishlistService.addProductToWishlist(wishlistDto);

        if(postedWishlistDto==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(postedWishlistDto);

    }

    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<List<WishlistDto>> getWishlistByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(wishlistService.getWishlistByUserId(userId));
    }

    @DeleteMapping("/wishlist")
    public ResponseEntity<Void> deleteProduct(@RequestBody WishlistDto wishlistDto){
        boolean deleted= wishlistService.removeProduct(wishlistDto);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
