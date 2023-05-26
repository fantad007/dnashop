package com.dnahealth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManageProductsDto {
    private long productId;
    private String category;
    private String productCode;
    private String productName;
    private double price;
}
