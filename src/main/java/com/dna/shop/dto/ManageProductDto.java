package com.dna.shop.dto;

import com.dna.shop.entity.ColorEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class ManageProductDto {
    private long productId;
    private long sizeId;
    private String category;
    private String productCode;
    private String productName;
    private double price;
    private String size;
    private List<String> colors;
    private Collection<ColorEntity> colorEntityList;
}
