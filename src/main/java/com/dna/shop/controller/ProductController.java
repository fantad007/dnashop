package com.dna.shop.controller;

import com.dna.shop.dto.ProductDetailDto;
import com.dna.shop.service.ProductService;
import com.dna.shop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ShoppingCartService cartService;

    @GetMapping("/san-pham")
    public String viewProducts(Model model) {
        model.addAttribute("TOTAL_QUANTITIES", cartService.getTotalQuantities());
        model.addAttribute("LIST_PRODUCT", productService.getAll());
        return "tat-ca-san-pham";
    }

    @GetMapping("/chi-tiet-san-pham")
    public String viewProductDetail(Model model,
                                    @RequestParam("id") String id) {
        String title = "DNA - 404";
        ProductDetailDto productDetailDto = productService.getProductDetailDto(id);
        if (productDetailDto != null) {
            title = "DNAHEALTH - " + productDetailDto.getProductName();
            model.addAttribute("title", title);
            model.addAttribute("TOTAL_QUANTITIES", cartService.getTotalQuantities());
            model.addAttribute("productDetail", productDetailDto);
        } else {
            model.addAttribute("title", title);
            model.addAttribute("TOTAL_QUANTITIES", cartService.getTotalQuantities());
            model.addAttribute("error", "CHÚNG TÔI RẤT TIẾC! SẢN PHẨM NÀY KHÔNG TỒN TẠI");
            return "404";
        }
        return "chi-tiet-san-pham";
    }
}
