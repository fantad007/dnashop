package com.dna.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto {
    private String productCode;
    private String productName;
    private double price;
    private String thumbnail;
    private String cartImage;
    private long categoryId;
}
