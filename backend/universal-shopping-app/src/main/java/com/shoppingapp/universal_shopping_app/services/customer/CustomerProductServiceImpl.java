package com.shoppingapp.universal_shopping_app.services.customer;

import com.shoppingapp.universal_shopping_app.dto.ProductDetailsDto;
import com.shoppingapp.universal_shopping_app.dto.ProductDto;
import com.shoppingapp.universal_shopping_app.entity.Product;
import com.shoppingapp.universal_shopping_app.entity.Review;
import com.shoppingapp.universal_shopping_app.repositry.ProductRepositry;
import com.shoppingapp.universal_shopping_app.repositry.ReviewRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService{

    private final ProductRepositry productRepositry;

    private  final ReviewRepositry reviewRepositry;

    public List<ProductDto> getAllProducts(){
        List<Product> products= productRepositry.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public ProductDetailsDto getProductDetailById(Long productId){
        Optional<Product> optionalProduct=productRepositry.findById(productId);

        if(optionalProduct.isPresent()){
            Product product=optionalProduct.get();

            List<Review> reviewList=reviewRepositry.findAllByProductId(productId);

            ProductDetailsDto productDetailsDto=new ProductDetailsDto();
            productDetailsDto.setProductDto(product.getDto());
            productDetailsDto.setReviewDtoList(reviewList.stream().map(Review::getDto).collect(Collectors.toList()));

            return productDetailsDto;
        }
        return null;
    }
}
