package com.dnahealth.controller;

import com.dnahealth.entity.Customer;
import com.dnahealth.service.ShoppingCartService;
import com.dnahealth.entity.OrderEntity;
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
            model.addAttribute("TOTAL_QUANTITIES", service.getTotalQuantities());
            return "thanh-toan-thanh-cong";
        } else {
            return "404";
        }
    }
}
