package com.shoppingapp.universal_shopping_app.services.auth;

import com.shoppingapp.universal_shopping_app.dto.SignupRequest;
import com.shoppingapp.universal_shopping_app.dto.UserDto;
import com.shoppingapp.universal_shopping_app.entity.Order;
import com.shoppingapp.universal_shopping_app.entity.User;
import com.shoppingapp.universal_shopping_app.enums.OrderStatus;
import com.shoppingapp.universal_shopping_app.enums.UserRole;
import com.shoppingapp.universal_shopping_app.repositry.OrderRepositry;
import com.shoppingapp.universal_shopping_app.repositry.UserRepositry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserRepositry userRepositry;


    @Autowired
    private OrderRepositry orderRepositry;

    public UserDto createUser(SignupRequest signupRequest){
        User user=new User();

        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        User createdUser= userRepositry.save(user);

        Order order=new Order();
        order.setAmount(0L);
        order.setTotalAmount(0L);
        order.setDiscount(0L);
        order.setUser(createdUser);
        order.setOrderStatus(OrderStatus.Pending);
        orderRepositry.save(order);




        UserDto userDto=new UserDto();
        userDto.setId(createdUser.getId());

        return userDto;
    }
    public Boolean hasUserWithEmail(String email){
        return userRepositry.findFirstByEmail(email).isPresent();
    }

    @PostConstruct
    public void createAdminAccount(){
        User adminAccount=userRepositry.findByRole(UserRole.ADMIN);
        if(null==adminAccount){
            User user=new User();
            user.setEmail("admin@account.com");
            user.setName("admin");
            user.setRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepositry.save(user);
        }
    }
}
