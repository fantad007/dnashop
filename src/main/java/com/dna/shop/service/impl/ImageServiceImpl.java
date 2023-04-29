package com.dna.shop.service.impl;

import com.dna.shop.dto.CreateImageDto;
import com.dna.shop.entity.ImageEntity;
import com.dna.shop.entity.ProductEntity;
import com.dna.shop.repository.ImageRepository;
import com.dna.shop.repository.ProductRepository;
import com.dna.shop.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ImageEntity> findByProductId(long proId) {
        return imageRepository.getImagesByProductId(proId);
    }

    @Override
    public void createImageForProduct(CreateImageDto createImageDto) {
        // Khoi tao doi tuong ImageEntity
        ImageEntity imageEntity = new ImageEntity();
        // Set gia tri cho ImageEntity
        imageEntity.setLink(createImageDto.getImageLink());
        long proId = createImageDto.getProductId();
        if (productRepository.findById(proId).isPresent()) {
            ProductEntity product = productRepository.findById(proId).get();
            imageEntity.setProduct(product);
        }
        imageRepository.save(imageEntity);
    }
}
