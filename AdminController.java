package com.example.ecom.web;

import com.example.ecom.model.Product;
import com.example.ecom.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class AdminController {
    private final ProductService productService;
    public AdminController(ProductService productService) { this.productService = productService; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.list(null));
        return "admin";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Product p) {
        productService.save(p);
        return "redirect:/admin/products";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }
}
