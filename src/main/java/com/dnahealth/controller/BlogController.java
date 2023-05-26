package com.dnahealth.controller;

import com.dnahealth.dto.BlogDetailDto;
import com.dnahealth.service.BlogService;
import com.dnahealth.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    ShoppingCartService cartService;

    @GetMapping("/chi-tiet-bai-viet")
    public String viewBlogDetail(Model model
                                    , @RequestParam("id") String id) {
        BlogDetailDto blogDetailDto = blogService.getBlogDetailDto(id);
        String title = "DNAHEALTH - 404";
        if (blogDetailDto != null) {
            title = "DNAHEALTH - " + blogDetailDto.getTitle().toUpperCase();
            model.addAttribute("title", title);
            model.addAttribute("TOTAL_QUANTITIES", cartService.getTotalQuantities());
            model.addAttribute("LIST_BLOG", blogService.getAll());
            model.addAttribute("blogDetail", blogDetailDto);
        } else {
            model.addAttribute("title", title);
            model.addAttribute("TOTAL_QUANTITIES", cartService.getTotalQuantities());
            model.addAttribute("error", "CHÚNG TÔI RẤT TIẾC! BÀI VIẾT NÀY KHÔNG TỒN TẠI");
            return "404";
        }
        return "chi-tiet-bai-viet";
    }
}
