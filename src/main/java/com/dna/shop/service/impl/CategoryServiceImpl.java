package com.dna.shop.service.impl;

import com.dna.shop.dto.CreateCategoryDto;
import com.dna.shop.entity.CategoryEntity;
import com.dna.shop.repository.CategoryRepository;
import com.dna.shop.service.CategoryService;
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
