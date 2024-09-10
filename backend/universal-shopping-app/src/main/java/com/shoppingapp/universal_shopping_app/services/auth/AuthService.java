package com.shoppingapp.universal_shopping_app.services.auth;

import com.shoppingapp.universal_shopping_app.dto.SignupRequest;
import com.shoppingapp.universal_shopping_app.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);

    Boolean hasUserWithEmail(String email);
}
