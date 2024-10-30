package com.janbat.shopuz.controller;

import com.janbat.shopuz.model.LoginForm;
import com.janbat.shopuz.model.RegisterForm;
import com.janbat.shopuz.model.User;
import com.janbat.shopuz.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }
    @PostMapping("/login")
    public String loginUser(@Valid LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        // Logika logowania
        return "redirect:/home";
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("registerForm") @Valid RegisterForm registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.registerNewUser(registerForm);
        return "redirect:/login";
    }
}

