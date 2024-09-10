package com.shoppingapp.universal_shopping_app.services.admin.adminproduct;

import com.shoppingapp.universal_shopping_app.dto.ProductDto;
import com.shoppingapp.universal_shopping_app.entity.Product;
import com.shoppingapp.universal_shopping_app.repositry.ProductRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService{

    private final ProductRepositry productRepositry;

    public ProductDto addProduct(ProductDto productDto) throws IOException {
        Product product=new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImg(productDto.getImg().getBytes());

        return productRepositry.save(product).getDto();
    }

    public List<ProductDto> getAllProducts(){
        List<Product> products= productRepositry.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id){
        Optional<Product> optionalProduct=productRepositry.findById(id);
        if(optionalProduct.isPresent()){
            productRepositry.deleteById(id);
            return true;
        }
        return false;
    }

    public ProductDto getProductById (Long productId){
        Optional<Product> optionalProduct=productRepositry.findById(productId);
        if(optionalProduct.isPresent()){
            return optionalProduct.get().getDto();
        }else{
            return null;
        }
    }

    public ProductDto updateProduct(Long productId,ProductDto productDto) throws IOException {
        Optional<Product> optionalProduct=productRepositry.findById(productId);
        if(optionalProduct.isPresent()){
            Product product=optionalProduct.get();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());

            if(productDto.getImg() != null){
                product.setImg(productDto.getImg().getBytes());
            }

            return productRepositry.save(product).getDto();
        }else{
            return null;
        }
    }
}
