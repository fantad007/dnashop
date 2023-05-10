package com.dna.shop.controller;

import com.dna.shop.entity.*;
import com.dna.shop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PayController {
    @Autowired
    ShoppingCartService service;

    @GetMapping("/thanh-toan")
    public String viewPayInformation(Model model) {
        OrderEntity orderEntity = service.getOrder();
        model.addAttribute("TOTAL_QUANTITIES", service.getTotalQuantities());
        model.addAttribute("order", orderEntity);
        model.addAttribute("information", new Customer());
        return "thanh-toan";
    }

    @PostMapping(value = "/process_customer_information")
    public String processCustomerInformation(Model model,
                                             @ModelAttribute("information") Customer information) {
        boolean save = service.saveCart(information);
        if (save) {
            return "redirect:/gio-hang";
        } else {
            return "404";
        }
    }
}
