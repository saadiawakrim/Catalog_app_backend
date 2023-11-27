package com.ym.service;

import com.ym.dto.PageProductDTO;
import com.ym.dto.ProductDTO;

public interface IProductService {
    public ProductDTO addProduct(ProductDTO productDTO);
    public ProductDTO updateProduct(Long id,ProductDTO productDTO);
    public void deleteProduct(Long id);
    public ProductDTO getProduct(Long id);
    public PageProductDTO getPageProduct(int page, int size);
    public PageProductDTO getPageProductByName(String keyword, int page, int size);
    public void changePromotion(Long id);

}
