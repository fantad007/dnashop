package com.dna.shop.service;

import com.dna.shop.dto.*;
import com.dna.shop.entity.ColorEntity;
import com.dna.shop.entity.ImageEntity;
import com.dna.shop.entity.ProductEntity;
import com.dna.shop.entity.SizeEntity;

import java.util.List;

public interface ProductService {
    void createCategory(CreateCategoryDto createCategoryDto);
    void createProduct(CreateProductDto createProductDto);
    void createSizeForProduct(CreateSizeDto createSizeDto);
    void createColorForProduct(CreateColorDto createColorDto);
    void createImageForProduct(CreateImageDto createImageDto);
    void createImageRepresentForProduct(CreateImageRepresentDto createImageRepresentDto);
    int remove(int id);
    List<ProductEntity> getAll();
    ProductEntity findProductById(long id);
    SizeEntity findSizeById(long id);
    ColorEntity findColorById(long id);
    List<ImageEntity> getImagesByColorId(long colorId);
    ProductDetailDto getProductDetailDto(String id);
    List<ManageProductDto> managesAllProducts();
}
