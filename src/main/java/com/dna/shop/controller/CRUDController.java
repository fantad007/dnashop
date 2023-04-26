package com.dna.shop.controller;

import com.dna.shop.dto.*;
import com.dna.shop.repository.CategoryRepository;
import com.dna.shop.repository.SizeRepository;
import com.dna.shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class CRUDController {
    private final CategoryRepository categoryRepository;
    private final SizeRepository sizeRepository;
    private final ProductService productService;

    //Controller Tạo mới Category
    @GetMapping(value = "/admin/tao-danh-muc")
    public String createCategoryPage(Model model) {
        model.addAttribute("categoryCreate", new CreateCategoryDto());
        return "admin/tao-danh-muc";
    }

    @PostMapping(value = "/admin/process_create_category")
    public String processCreateCategory(Model model,
                                       @ModelAttribute("categoryCreate") CreateCategoryDto createCategoryDto) {
        productService.createCategory(createCategoryDto);
        return "redirect:/admin/tao-moi-san-pham";
    }

    //Controller Tạo mới Product
    @GetMapping(value = "/admin/tao-moi-san-pham")
    public String createProductPage(Model model) {
        model.addAttribute("productCreate", new CreateProductDto());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("sizes", sizeRepository.findAll());
        return "admin/tao-san-pham";
    }

    @PostMapping(value = "/admin/process_create_product")
    public String processCreateProduct(Model model,
                                       @ModelAttribute("productCreate") CreateProductDto createProductDto) {
        productService.createProduct(createProductDto);
        return "redirect:/admin/tao-size";
    }

    //Controller Tạo mới Size
    @GetMapping(value = "/admin/tao-size")
    public String createSizePage(Model model) {
        model.addAttribute("sizeCreate", new CreateSizeDto());
        model.addAttribute("products", productService.getAll());
        return "admin/tao-size";
    }

    @PostMapping(value = "/admin/process_create_size")
    public String processCreateSize(Model model,
                                       @ModelAttribute("sizeCreate") CreateSizeDto createSizeDto) {
        productService.createSizeForProduct(createSizeDto);
        return "redirect:/admin/tao-size";
    }

    //Controller Tạo mới Color
    @GetMapping(value = "/admin/tao-mau-sac/{sizeId}")
    public String createColorPage(Model model,
                                  @PathVariable("sizeId") Long sizeId) {
        CreateColorDto colorDto = new CreateColorDto();
        colorDto.setSizeId(sizeId);
        model.addAttribute("colorCreate", colorDto);
        return "admin/tao-mau-sac";
    }

    @PostMapping(value = "/admin/process_create_color")
    public String processCreateColor(Model model,
                                       @ModelAttribute("colorCreate") CreateColorDto createColorDto) {
        productService.createColorForProduct(createColorDto);
        return "redirect:/admin/quan-ly-san-pham";
    }

    //Controller Tạo mới Image
    @GetMapping(value = "/admin/tao-hinh-anh/{colorId}")
    public String createImagePage(Model model,
                                  @PathVariable("colorId") Long colorId) {
        CreateImageDto imageDto = new CreateImageDto();
        imageDto.setColorId(colorId);
        model.addAttribute("imageCreate", imageDto);
        return "admin/tao-hinh-anh";
    }

    @PostMapping(value = "/admin/process_create_image")
    public String processCreateImage(Model model,
                                     @ModelAttribute("imageCreate") CreateImageDto createImageDto) {
        productService.createImageForProduct(createImageDto);
        return "redirect:/admin/quan-ly-san-pham";
    }

    //Controller Tạo mới Image
    @GetMapping(value = "/admin/tao-anh-dai-dien/{productId}")
    public String createImageRepresentPage(Model model,
                                  @PathVariable("productId") Long productId) {
        CreateImageRepresentDto createImageRepresentDto = new CreateImageRepresentDto();
        createImageRepresentDto.setProductId(productId);
        model.addAttribute("imageRepresentCreate", createImageRepresentDto);
        return "admin/tao-anh-dai-dien";
    }

    @PostMapping(value = "/admin/process_create_image_represent")
    public String processCreateImageRepresent(Model model,
                                     @ModelAttribute("imageRepresentCreate") CreateImageRepresentDto createImageRepresentDto) {
        productService.createImageRepresentForProduct(createImageRepresentDto);
        return "redirect:/admin/quan-ly-san-pham";
    }
}
