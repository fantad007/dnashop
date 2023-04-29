package com.dna.shop.service;

import com.dna.shop.dto.CreateImageDto;
import com.dna.shop.entity.ImageEntity;

import java.util.List;

public interface ImageService {
    List<ImageEntity> findByProductId(long proId);
    void createImageForProduct(CreateImageDto createImageDto);
}
