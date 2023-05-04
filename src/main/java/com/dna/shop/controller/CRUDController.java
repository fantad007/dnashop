package com.dna.shop.controller;

import com.dna.shop.dto.*;
import com.dna.shop.service.CategoryService;
import com.dna.shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class CRUDController {
    private final CategoryService categoryService;
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
        categoryService.createCategory(createCategoryDto);
        return "redirect:/admin/tao-moi-san-pham";
    }

    //Controller Tạo mới Product
    @GetMapping(value = "/admin/tao-moi-san-pham")
    public String createProductPage(Model model) {
        model.addAttribute("productCreate", new CreateProductDto());
        model.addAttribute("categories", categoryService.findAll());
        return "admin/tao-san-pham";
    }

    @PostMapping(value = "/admin/process_create_product")
    public String processCreateProduct(Model model,
                                       @ModelAttribute("productCreate") CreateProductDto createProductDto) {
        productService.createProduct(createProductDto);
        return "redirect:/san-pham";
    }
}
