package com.shoppingapp.universal_shopping_app.entity;

import com.shoppingapp.universal_shopping_app.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name="users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private UserRole role;

    @Lob
    @Column(columnDefinition = "Longblob")
    private byte[] img;

}
