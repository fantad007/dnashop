package com.dna.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateColorDto {
    private long sizeId;
    private String color;
    private int quantity;
}
