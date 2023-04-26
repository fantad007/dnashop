package com.dna.shop.service.impl;

import com.dna.shop.dto.*;
import com.dna.shop.entity.*;
import com.dna.shop.repository.*;
import com.dna.shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final SizeDetailRepository sizeDetailRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final ImageRepresentRepository imageRepresentRepository;

    @Override
    public void createCategory(CreateCategoryDto createCategoryDto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(createCategoryDto.getCategory());
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void createProduct(CreateProductDto createProductDto) {
        // Khoi tao doi tuong ProductEntity
        ProductEntity productEntity = new ProductEntity();
        // Set gia tri cho ProductEntity
        productEntity.setProductCode(createProductDto.getProductCode());
        productEntity.setName(createProductDto.getProductName());
        productEntity.setPrice(createProductDto.getPrice());
        productEntity.setThumbnail(createProductDto.getThumbnail());
        if (categoryRepository.findById(createProductDto.getCategoryId()).isPresent()) {
            CategoryEntity categoryEntity = categoryRepository.findById(createProductDto.getCategoryId()).get();
            productEntity.setCategory(categoryEntity);
        }
        productRepository.save(productEntity);
    }

    @Override
    public void createSizeForProduct(CreateSizeDto createSizeDto) {
        // Khoi tao doi tuong SizeEntity, SizeDetailEntity
        SizeEntity sizeEntity = new SizeEntity();
        SizeDetailEntity sizeDetailEntity = new SizeDetailEntity();
        // Set gia tri cho SizeEntity
        sizeEntity.setSize(createSizeDto.getSize());
        // Set gia tri cho SizeDetailEntity
        sizeDetailEntity.setShirtLength(createSizeDto.getShirtLength());
        sizeDetailEntity.setChestCircumference(createSizeDto.getChestCircumference());
        sizeDetailEntity.setShoulderWidth(createSizeDto.getShoulderWidth());
        sizeDetailEntity.setSleeveLength(createSizeDto.getSleeveLength());
        sizeDetailEntity.setWeight(createSizeDto.getWeight());
        sizeDetailEntity.setHeight(createSizeDto.getHeight());
        if (productRepository.findById(createSizeDto.getProductId()).isPresent()) {
            ProductEntity productEntity = productRepository.findById(createSizeDto.getProductId()).get();
            sizeEntity.setProduct(productEntity);
            sizeDetailEntity.setProduct(productEntity);
        }
        sizeDetailEntity.setSize(sizeEntity);
        sizeRepository.save(sizeEntity);
        sizeDetailRepository.save(sizeDetailEntity);
    }

    @Override
    public void createColorForProduct(CreateColorDto createColorDto) {
        // Khoi tao doi tuong ColorEntity
        ColorEntity colorEntity = new ColorEntity();
        // Set gia tri cho ColorEntity
        colorEntity.setName(createColorDto.getColor());
        colorEntity.setQuantity(createColorDto.getQuantity());
        if (sizeRepository.findById(createColorDto.getSizeId()).isPresent()) {
            SizeEntity sizeEntity = sizeRepository.findById(createColorDto.getSizeId()).get();
            colorEntity.setSize(sizeEntity);
        }
        colorRepository.save(colorEntity);
    }

    @Override
    public void createImageForProduct(CreateImageDto createImageDto) {
        // Khoi tao doi tuong ImageEntity
        ImageEntity imageEntity = new ImageEntity();
        // Set gia tri cho ImageEntity
        imageEntity.setLink(createImageDto.getImageLink());
        if (colorRepository.findById(createImageDto.getColorId()).isPresent()) {
            ColorEntity colorEntity = colorRepository.findById(createImageDto.getColorId()).get();
            imageEntity.setColor(colorEntity);
        }
        imageRepository.save(imageEntity);
    }

    @Override
    public void createImageRepresentForProduct(CreateImageRepresentDto createImageRepresentDto) {
        // Khoi tao doi tuong ImageRepresentEntity
        ImageRepresentEntity imageRepresentEntity = new ImageRepresentEntity();
        // Set gia tri cho ImageRepresentEntity
        imageRepresentEntity.setLink(createImageRepresentDto.getLink());
        if (productRepository.findById(createImageRepresentDto.getProductId()).isPresent()) {
            ProductEntity productEntity = productRepository.findById(createImageRepresentDto.getProductId()).get();
            imageRepresentEntity.setProduct(productEntity);
        }
        imageRepresentRepository.save(imageRepresentEntity);
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
    public SizeEntity findSizeById(long id) {
        if (sizeRepository.findById(id).isPresent()) {
            return sizeRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public ColorEntity findColorById(long id) {
        if (colorRepository.findById(id).isPresent()) {
            return colorRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<ImageEntity> getImagesByColorId(long colorId) {
        return imageRepository.getImagesByColorId(colorId);
    }

    @Override
    public ProductDetailDto getProductDetailDto(String id) {
        ProductDetailDto productDetailDto = new ProductDetailDto();
        try {
            long productId = Long.parseLong(id);
            ProductEntity productEntity = findProductById(productId);
            if (productEntity != null) {
                productDetailDto.setId(productEntity.getId());
                productDetailDto.setProductName(productEntity.getName());
                productDetailDto.setPrice(productEntity.getPrice());
                productDetailDto.setShortDesc(productEntity.getShortDesc());
                productDetailDto.setFullDesc(productEntity.getFullDesc());
                productDetailDto.setSizes(productEntity.getSizes());
                if (categoryRepository.findByProductId(productId).isPresent()) {
                    CategoryEntity category = categoryRepository.findByProductId(productId).get();
                    productDetailDto.setCategory(category);
                }
                List<ImageEntity> imageList = new ArrayList<>();
                for (SizeEntity size : productEntity.getSizes()) {
                    for (ColorEntity color : size.getColors()) {
                        List<ImageEntity> images = getImagesByColorId(color.getId());
                        imageList.addAll(images);
                    }
                }
                List<ImageRepresentEntity> imageRepresents = imageRepresentRepository.getImageRepresentsByProductId(productId);
                productDetailDto.setImageRepresents(imageRepresents);
                productDetailDto.setImages(imageList);
                return productDetailDto;
            } else {
                return null;
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public List<ManageProductDto> managesAllProducts() {
        List<ManageProductDto> manageProductDtoList = new ArrayList<>();
        List<ProductEntity> productList = productRepository.findAll();
        if (!productList.isEmpty()) {
            for (ProductEntity product : productList) {
                if (!product.getSizes().isEmpty()) {
                    for (SizeEntity size : product.getSizes()) {
                        ManageProductDto manageProductDto = new ManageProductDto();
                        manageProductDto.setProductId(product.getId());
                        manageProductDto.setSizeId(size.getId());
                        manageProductDto.setCategory(product.getCategory().getName());
                        manageProductDto.setProductCode(product.getProductCode());
                        manageProductDto.setProductName(product.getName());
                        manageProductDto.setPrice(product.getPrice());
                        manageProductDto.setSize(size.getSize());
                        List<String> colors = new ArrayList<>();
                        for (ColorEntity color : size.getColors()) {
                            colors.add(color.getName());
                        }
                        manageProductDto.setColors(colors);
                        manageProductDto.setColorEntityList(size.getColors());
                        manageProductDtoList.add(manageProductDto);
                    }
                }
            }
        }
        return manageProductDtoList;
    }
}






















