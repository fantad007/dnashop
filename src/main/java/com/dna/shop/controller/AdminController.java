package com.dna.shop.controller;

import com.dna.shop.dto.ManageProductDto;
import com.dna.shop.dto.OrderDto;
import com.dna.shop.service.OrderService;
import com.dna.shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {
    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping("/admin/dashboard")
    public String dashboardViews(Model model) {
        List<OrderDto> orderDtoList = orderService.viewsAllOrders();
        model.addAttribute("orders", orderDtoList);
        return "admin/dashboard";
    }

    @GetMapping("/admin/quan-ly-san-pham")
    public String manageProductViews(Model model) {
        List<ManageProductDto> manageProductDtoList = productService.managesAllProducts();
        model.addAttribute("manageProductDtos", manageProductDtoList);
        return "admin/quan-ly-san-pham";
    }
}
