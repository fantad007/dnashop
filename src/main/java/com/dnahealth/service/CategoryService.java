package com.dnahealth.service;

import com.dnahealth.dto.CreateCategoryDto;
import com.dnahealth.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> findAll();
    void createCategory(CreateCategoryDto createCategoryDto);
    CategoryEntity findCategoryById(long id);
    CategoryEntity findByProductId(long proId);
}
