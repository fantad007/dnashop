package com.dna.shop.controller;

import com.dna.shop.entity.*;
import com.dna.shop.entity.ProductEntity;
import com.dna.shop.service.ProductService;
import com.dna.shop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingCartController {
    @Autowired
    ProductService productService;
    @Autowired
    ShoppingCartService cartService;

    @GetMapping(value = {"/gio-hang"})
    public String viewCarts(Model model) {
        model.addAttribute("TOTAL_QUANTITIES", cartService.getTotalQuantities());
        model.addAttribute("CART_ITEMS", cartService.getAllCartItems());
        model.addAttribute("TOTAL", cartService.getAmount());
        return "gio-hang";
    }

    @GetMapping("/gio-hang/them/{productId}")
    public String addToCart(@PathVariable("productId") Long productId) {
        ProductEntity productEntity = productService.findProductById(productId);
        CartEntity cart = new CartEntity();
        if (productEntity != null) {
            cart.setProduct(productEntity);
        }
        cart.setQuantity(1);
        cartService.add(cart);
        return "redirect:/gio-hang";
    }

    @GetMapping("/gio-hang/lam-moi")
    public String clearCart() {
        cartService.clear();
        return "redirect:/gio-hang";
    }

    @GetMapping("/gio-hang/xoa/{id}")
    public String removeCart(@PathVariable("id") Long id) {
        cartService.remove(id);
        return "redirect:/gio-hang";
    }

    @PostMapping("/gio-hang/cap-nhat")
    public String update(@RequestParam("id") String strId,
                         @RequestParam("quantity") String strQuantity) {
        long id = Long.parseLong(strId);
        int quantity = Integer.parseInt(strQuantity);
        cartService.update(id, quantity);
        return "redirect:/gio-hang";
    }
}
