package com.dna.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSizeDto {
    private long productId;
    private String size;
    private String shirtLength;
    private String chestCircumference;
    private String shoulderWidth;
    private String sleeveLength;
    private String weight;
    private String height;
}
