package com.dnahealth.controller;

import com.dnahealth.dto.ManageProductsDto;
import com.dnahealth.dto.OrderDto;
import com.dnahealth.service.OrderService;
import com.dnahealth.service.ProductService;
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
        List<ManageProductsDto> manageProductsDtoList = productService.managesProducts();
        model.addAttribute("manageProductDtos", manageProductsDtoList);
        return "admin/quan-ly-san-pham";
    }
}
