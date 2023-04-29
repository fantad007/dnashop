package com.dna.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ManageProductsDto {
    private long productId;
    private String category;
    private String productCode;
    private String productName;
    private double price;
    private List<String> imageLinks;
}
