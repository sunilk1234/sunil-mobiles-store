package com.example.ecom.web;

import com.example.ecom.model.Cart;
import com.example.ecom.model.Order;
import com.example.ecom.model.User;
import com.example.ecom.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    private final OrderService orderService;
    public CheckoutController(OrderService orderService) { this.orderService = orderService; }

    @GetMapping
    public String checkout() { return "checkout"; }

    @PostMapping
    public String place(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) return "redirect:/home";
        Order order = orderService.placeOrder(user, cart);
        cart.clear();
        model.addAttribute("orderId", order.getId());
        return "order-success";
    }
}
