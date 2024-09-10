package com.shoppingapp.universal_shopping_app.repositry;

import com.shoppingapp.universal_shopping_app.entity.User;
import com.shoppingapp.universal_shopping_app.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositry extends JpaRepository<User, Long> {
  Optional<User> findFirstByEmail(String email);

  User findByRole(UserRole userRole);
}
