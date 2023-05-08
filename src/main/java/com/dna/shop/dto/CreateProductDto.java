package com.dna.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto {
    private String productCode;
    private String productName;
    private String trademark;
    private double price;
    private long categoryId;
}
