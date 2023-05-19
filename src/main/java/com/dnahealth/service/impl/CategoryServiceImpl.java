package com.dnahealth.service.impl;

import com.dnahealth.dto.CreateCategoryDto;
import com.dnahealth.entity.CategoryEntity;
import com.dnahealth.repository.CategoryRepository;
import com.dnahealth.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(CreateCategoryDto createCategoryDto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(createCategoryDto.getCategory());
        categoryEntity.setCategoryCode(createCategoryDto.getCategoryCode());
        categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity findCategoryById(long id) {
        if (categoryRepository.findById(id).isPresent()) {
            return categoryRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public CategoryEntity findByProductId(long proId) {
        if (categoryRepository.findByProductId(proId).isPresent()) {
            return categoryRepository.findByProductId(proId).get();
        }
        return null;
    }
}
