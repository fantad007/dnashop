package com.dnahealth.service.impl;

import com.dnahealth.dto.CreateProductDto;
import com.dnahealth.dto.ManageProductsDto;
import com.dnahealth.dto.ProductDetailDto;
import com.dnahealth.entity.ProductEntity;
import com.dnahealth.repository.ProductRepository;
import com.dnahealth.service.CategoryService;
import com.dnahealth.service.ProductService;
import com.dnahealth.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public void createProduct(CreateProductDto createProductDto) {
        // Khoi tao doi tuong ProductEntity
        ProductEntity productEntity = new ProductEntity();
        // Set gia tri cho ProductEntity
        productEntity.setProductCode(createProductDto.getProductCode());
        productEntity.setName(createProductDto.getProductName());
        productEntity.setTrademark(createProductDto.getTrademark());
        productEntity.setPrice(createProductDto.getPrice());
        CategoryEntity category = categoryService.findCategoryById(createProductDto.getCategoryId());
        if (category != null) {
            productEntity.setCategory(category);
        }
        productRepository.save(productEntity);
    }

    @Override
    public int remove(int id) {
        return 0;
    }

    @Override
    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity findProductById(long id) {
        if (productRepository.findById(id).isPresent()) {
            return productRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public ProductDetailDto getProductDetailDto(String id) {
        ProductDetailDto productDetailDto = new ProductDetailDto();
        try {
            long productId = Long.parseLong(id);
            ProductEntity product = findProductById(productId);
            if (product != null) {
                productDetailDto.setId(product.getId());
                productDetailDto.setProductCode(product.getProductCode());
                productDetailDto.setProductName(product.getName());
                productDetailDto.setTrademark(product.getTrademark());
                productDetailDto.setPrice(product.getPrice());
                productDetailDto.setShortDesc(product.getShortDesc());
                productDetailDto.setFullDesc(product.getFullDesc());
                CategoryEntity category = categoryService.findByProductId(productId);
                if (category != null) {
                    productDetailDto.setCategory(category);
                }
                return productDetailDto;
            } else {
                return null;
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public List<ManageProductsDto> managesProducts() {
        List<ManageProductsDto> manageProductsDtoList = new ArrayList<>();
        List<ProductEntity> products = productRepository.findAll();
        if (!products.isEmpty()) {
            for (ProductEntity product : products) {
                ManageProductsDto manageProductsDto = new ManageProductsDto();
                manageProductsDto.setProductId(product.getId());
                manageProductsDto.setCategory(product.getCategory().getName());
                manageProductsDto.setProductCode(product.getProductCode());
                manageProductsDto.setProductName(product.getName());
                manageProductsDto.setPrice(product.getPrice());
                manageProductsDtoList.add(manageProductsDto);
            }
        }
        return manageProductsDtoList;
    }
}






















