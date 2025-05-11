package ru.kata.spring.boot_security.demo.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String adminPage(@AuthenticationPrincipal User user, Model model) {
        System.out.println("🔐 Зашёл админ: " + user.getUsername());
        System.out.println("👉 Роли: " + user.getRoles());
        model.addAttribute("admin", user);
        return "admin";
    }
}
