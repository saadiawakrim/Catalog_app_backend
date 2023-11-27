package com.ym.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor @Setter @Getter
public class PageProductDTO {

    private int page;
    private int size;
    private int totalPages;
    private List<ProductDTO> products;
}
