package com.dna.shop.service;

import com.dna.shop.dto.CreateCategoryDto;
import com.dna.shop.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> findAll();
    void createCategory(CreateCategoryDto createCategoryDto);
    CategoryEntity findCategoryById(long id);
    CategoryEntity findByProductId(long proId);
}
