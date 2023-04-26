package com.dna.shop.controller;

import com.dna.shop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    ShoppingCartService cartService;

    @GetMapping("/")
    public String homeView(Model model) {
        model.addAttribute("TOTAL_QUANTITIES", cartService.getTotalQuantities());
        return "/trang-chu";
    }
}
