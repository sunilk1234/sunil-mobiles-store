package com.example.ecom.web;

import com.example.ecom.model.User;
import com.example.ecom.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Validated
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) { this.userService = userService; }

    @GetMapping("/login")
    public String loginForm() { return "login"; }

    @PostMapping("/login")
    public String login(@RequestParam @Email String email,
                        @RequestParam @NotBlank String password,
                        HttpSession session,
                        Model model) {
        User u = userService.authenticate(email, password);
        if (u == null) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
        session.setAttribute("user", u);
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String registerForm() { return "register"; }

    @PostMapping("/register")
    public String register(@RequestParam @NotBlank String name,
                           @RequestParam @Email String email,
                           @RequestParam @NotBlank String password,
                           HttpSession session,
                           Model model) {
        if (userService.emailExists(email)) {
            model.addAttribute("error", "Email already registered");
            return "register";
        }
        User u = userService.register(name, email, password);
        session.setAttribute("user", u);
        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }
}
