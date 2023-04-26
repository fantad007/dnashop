package com.dna.shop.controller;

import com.dna.shop.entity.*;
import com.dna.shop.repository.*;
import com.dna.shop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class PayController {
    @Autowired
    ShoppingCartService service;
    @Autowired
    ShoppingCartService cartService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @GetMapping("/thanh-toan")
    public String viewPayInformation(Model model) {
        OrderDetail orderDetail = service.getOrderDetail();
        model.addAttribute("orderDetail", orderDetail);
        model.addAttribute("information", new Customer());
        return "thanh-toan";
    }

    @PostMapping(value = "/process_customer_information")
    public String processCustomerInformation(Model model,
                                             @ModelAttribute("information") Customer information) {
        try {
            OrderDetail orderDetail = service.getOrderDetail();
            Collection<OrderDetail> orderDetails = new ArrayList<>();
            orderDetails.add(orderDetail);
            information.setOrderDetails(orderDetails);
            customerRepository.save(information);
            orderDetail.setCustomer(information);
            orderDetailRepository.save(orderDetail);
            for (CartEntity cart : orderDetail.getCart()) {
                cart.setOrderDetail(orderDetail);
                cartRepository.save(cart);
            }
            cartService.clear();
            return "redirect:/gio-hang";

        } catch (Exception e) {
            model.addAttribute("error", e);
            return "404";
        }
    }
}
