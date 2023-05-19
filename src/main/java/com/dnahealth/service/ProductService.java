package com.dnahealth.service;

import com.dnahealth.entity.ProductEntity;
import com.dnahealth.dto.CreateProductDto;
import com.dnahealth.dto.ManageProductsDto;
import com.dnahealth.dto.ProductDetailDto;

import java.util.List;

public interface ProductService {
    void createProduct(CreateProductDto createProductDto);
    int remove(int id);
    List<ProductEntity> getAll();
    ProductEntity findProductById(long id);
    ProductDetailDto getProductDetailDto(String id);
    List<ManageProductsDto> managesProducts();
}
