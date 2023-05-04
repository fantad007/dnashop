package com.dna.shop.dto;

import com.dna.shop.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class ProductDetailDto {
    private Long id;
    private String productCode;
    private String productName;
    private double price;
    private String shortDesc;
    private String fullDesc;
    private CategoryEntity category;
}
