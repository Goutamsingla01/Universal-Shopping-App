package com.shoppingapp.universal_shopping_app.dto;

import com.shoppingapp.universal_shopping_app.enums.UserRole;
import com.shoppingapp.universal_shopping_app.repositry.UserRepositry;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private UserRole userRole;
}
