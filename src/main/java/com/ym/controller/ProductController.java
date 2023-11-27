package com.ym.controller;

import com.ym.dto.PageProductDTO;
import com.ym.dto.ProductDTO;
import com.ym.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class ProductController {

    private IProductService productService;

    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO){
        return productService.addProduct(productDTO);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @GetMapping("/{id}")
    ProductDTO getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }

    @GetMapping
    public PageProductDTO getPageProducts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name="size", defaultValue = "5") int size
            ){
        return productService.getPageProduct(page, size);
    }

    @GetMapping("/name/like")
    public PageProductDTO getPageProductsByName(
            @RequestParam(name="keyword", defaultValue = "") String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name="size", defaultValue = "5") int size
    ){
        return productService.getPageProductByName(keyword,page, size);
    }

    @PatchMapping("/{id}/promotion")
    public void changePromotion(@PathVariable Long id){
        productService.changePromotion(id);
    }
}
