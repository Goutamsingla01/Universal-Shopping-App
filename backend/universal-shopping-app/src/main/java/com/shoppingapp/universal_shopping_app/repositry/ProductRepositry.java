package com.shoppingapp.universal_shopping_app.repositry;

import com.shoppingapp.universal_shopping_app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositry extends JpaRepository<Product, Long> {
}
