package com.ym.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private boolean promotion;
}
