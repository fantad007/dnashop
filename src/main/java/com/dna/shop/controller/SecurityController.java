package com.dna.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping(value = "/dang-nhap")
    public String loginPage(Model model) {
        return "admin/security/dang-nhap";
    }

    @GetMapping(value = "/dang-nhap-that-bai")
    public String loginError(Model model) {
        model.addAttribute("hasError", true);
        model.addAttribute("messageError", "Email hoặc mật khẩu không chính xác");
        return "admin/security/dang-nhap";
    }

    @GetMapping(value = "/dang-xuat-thanh-cong")
    public String logoutSuccessful() {
        return "admin/security/dang-nhap";
    }
}
