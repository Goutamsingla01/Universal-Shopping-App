package com.shoppingapp.universal_shopping_app.entity;

import com.shoppingapp.universal_shopping_app.dto.ProductDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private Long price;


    @Lob
    private String description;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    public ProductDto getDto(){
        ProductDto productDto=new ProductDto();

        productDto.setId(id);
        productDto.setName(name);
        productDto.setPrice(price);
        productDto.setDescription(description);
        productDto.setByteImg(img);
        return productDto;

    }

}
