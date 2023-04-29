package com.dna.shop.service;

import com.dna.shop.dto.*;
import com.dna.shop.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    void createProduct(CreateProductDto createProductDto);
    int remove(int id);
    List<ProductEntity> getAll();
    ProductEntity findProductById(long id);
    ProductDetailDto getProductDetailDto(String id);
    List<ManageProductsDto> managesProducts();
}
