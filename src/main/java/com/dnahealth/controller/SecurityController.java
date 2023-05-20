package com.dnahealth.controller;

import com.dnahealth.entity.AdminEntity;
import com.dnahealth.service.AdminService;
import com.dnahealth.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SecurityController {
    @Autowired
    AdminService adminService;
    @Autowired
    ShoppingCartService cartService;

    @GetMapping(value = "/dang-nhap")
    public String loginPage(Model model) {
        model.addAttribute("TOTAL_QUANTITIES", cartService.getTotalQuantities());
        return "admin/security/dang-nhap";
    }

    @GetMapping(value = "/dang-nhap-that-bai")
    public String loginError(Model model) {
        model.addAttribute("hasError", true);
        model.addAttribute("messageError", "Email hoặc mật khẩu không chính xác");
        model.addAttribute("TOTAL_QUANTITIES", cartService.getTotalQuantities());
        return "admin/security/dang-nhap";
    }

    @GetMapping(value = "/dang-xuat-thanh-cong")
    public String logoutSuccessful() {
        return "admin/security/dang-nhap";
    }

    @GetMapping("/dang-ky")
    public String registerEmployee(Model model) {
        model.addAttribute("adminRegister", new AdminEntity());
        model.addAttribute("TOTAL_QUANTITIES", cartService.getTotalQuantities());
        return "admin/security/dang-ky";
    }

    @PostMapping("/process_register")
    public String processRegister(@ModelAttribute("adminRegister") AdminEntity admin,
                                  HttpServletRequest request) {
        adminService.register(admin, getSiteURL(request));
        return "admin/security/dang-ky-thanh-cong";
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (adminService.verify(code)) {
            return "admin/security/verify-thanh-cong";
        } else {
            return "admin/security/verify-that-bai";
        }
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
