package com.ym.service;

import com.ym.dto.PageProductDTO;
import com.ym.dto.ProductDTO;
import com.ym.exception.ProductNotFoundException;
import com.ym.mapper.ProductMapper;
import com.ym.model.Product;
import com.ym.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService implements IProductService{

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductDTO addProduct(ProductDTO productDTO){
        Product product = productMapper.toProduct(productDTO);
        product.setId(null);
        productRepository.save(product);
        return productMapper.toProductDTO(product);
    }

    public ProductDTO updateProduct(Long id,ProductDTO productDTO){
        if(!productRepository.existsById(id))
            throw new ProductNotFoundException("Product not found");
        Product product = productMapper.toProduct(productDTO);
        product.setId(id);
        productRepository.save(product);
        return productMapper.toProductDTO(product);
    }

    public void deleteProduct(Long id){
        if(!productRepository.existsById(id))
            throw new ProductNotFoundException("Product not found");
        productRepository.deleteById(id);
    }

    public ProductDTO getProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product not found")
        );
        return productMapper.toProductDTO(product);
    }

    public PageProductDTO getPageProduct(int page, int size){
        Page<Product> pageProducts = productRepository.findAll(PageRequest.of(page,size));
        List<ProductDTO> productDTOS =
                pageProducts.getContent().stream()
                        .map((x) -> productMapper.toProductDTO(x))
                        .toList();
        return new PageProductDTO(page, size, pageProducts.getTotalPages(), productDTOS);
    }

    public PageProductDTO getPageProductByName(String keyword, int page, int size){
        Page<Product> pageProducts = productRepository.findByNameContains(keyword, PageRequest.of(page,size));
        List<ProductDTO> productDTOS =
                pageProducts.getContent().stream()
                        .map(x -> productMapper.toProductDTO(x))
                        .toList();
        return new PageProductDTO(page, size, pageProducts.getTotalPages(), productDTOS);
    }

    public void changePromotion(Long id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product not found")
        );
        product.setPromotion(!product.isPromotion());
        productRepository.save(product);
    }
}
