package com.ym.mapper;

import com.ym.dto.ProductDTO;
import com.ym.model.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(ProductDTO productDTO){
        if(productDTO != null){
            Product product = new Product();
            BeanUtils.copyProperties(productDTO, product);
            return product;
        }
        return null;
    }

    public ProductDTO toProductDTO(Product product){
        if(product != null){
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(product, productDTO);
            return productDTO;
        }
        return null;
    }
}
