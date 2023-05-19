package com.dnahealth.dto;

import com.dnahealth.entity.CategoryEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailDto {
    private Long id;
    private String productCode;
    private String productName;
    private String trademark;
    private double price;
    private String shortDesc;
    private String fullDesc;
    private CategoryEntity category;
}
