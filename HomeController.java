package com.example.ecom.web;

import com.example.ecom.model.Product;
import com.example.ecom.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    private final ProductService productService;
    public HomeController(ProductService productService) { this.productService = productService; }

    @GetMapping({"/", "/home"})
    public String home(@RequestParam(value = "q", required = false) String q, Model model) {
        List<Product> products = productService.list(q);
        model.addAttribute("products", products);
        model.addAttribute("q", q);
        return "home";
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable Long id, Model model) {
        Product p = productService.get(id);
        if (p == null) return "redirect:/home";
        model.addAttribute("product", p);
        return "product";
    }
}
