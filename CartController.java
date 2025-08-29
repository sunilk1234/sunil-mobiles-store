package com.example.ecom.web;

import com.example.ecom.model.Cart;
import com.example.ecom.model.Product;
import com.example.ecom.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final ProductService productService;
    public CartController(ProductService productService) { this.productService = productService; }

    private Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        model.addAttribute("cart", getCart(session));
        return "cart";
    }

    @PostMapping("/add")
    public String add(@RequestParam Long id, @RequestParam int qty, HttpSession session) {
        Product p = productService.get(id);
        if (p != null) getCart(session).add(p, qty);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String update(@RequestParam Long id, @RequestParam int qty, HttpSession session) {
        getCart(session).update(id, qty);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam Long id, HttpSession session) {
        getCart(session).remove(id);
        return "redirect:/cart";
    }
}
