package com.shoppingapp.universal_shopping_app.entity;

import com.shoppingapp.universal_shopping_app.dto.CartItemsDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long price;

    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="product_id", nullable =false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Product product;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="user_id", nullable =false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private User user;

//    @OneToOne(fetch=FetchType.LAZY)   //many to one  like one order can have many cartitems
//    @JoinColumn(name="order_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id" ,nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;

    public CartItemsDto getCartDto(){
        CartItemsDto cartItemsDto=new CartItemsDto();
        cartItemsDto.setId(id);
        cartItemsDto.setPrice(price);
        cartItemsDto.setProductId(product.getId());
        cartItemsDto.setQuantity(quantity);
        cartItemsDto.setUserId(user.getId());
        cartItemsDto.setProductName(product.getName());
        cartItemsDto.setReturnedImg(product.getImg());

        return cartItemsDto;
    }
}