package com.janbat.shopuz.controller;

import com.janbat.shopuz.captcha.CaptchaSettings;
import com.janbat.shopuz.model.LoginForm;
import com.janbat.shopuz.model.RegisterForm;
import com.janbat.shopuz.captcha.CaptchaService;
import com.janbat.shopuz.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;
    private final CaptchaService captchaService;
    private final CaptchaSettings captchaSettings;

    @Autowired
    public AuthController(UserService userService, CaptchaService captchaService, CaptchaSettings captchaSettings) {
        this.userService = userService;
        this.captchaService = captchaService;
        this.captchaSettings = captchaSettings;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("captchaSettings", captchaSettings);
        System.out.println("Username:"); // Debugging
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("g-recaptcha-response") String captchaResponse, @Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        System.out.println("Username1:"); // Debugging
        if (bindingResult.hasErrors()) {
            return "login";
        }
        if (!captchaService.verifyCaptcha(captchaResponse)) {
            model.addAttribute("captchaError", "Invalid Captcha");
            return "login";
        }
        // Logika logowania
        String username = loginForm.getUsername(); // Zakładam, że LoginForm ma metodę getUsername()
        System.out.println("Username: " + username); // Debugging
        if (username != null) {
            request.getSession().setAttribute("username", username); // Ustawienie nazwy użytkownika w sesji
            System.out.println("Username: " + username); // Debugging
        } else {
            System.out.println("Username is null"); // Debugging
        }
        return "redirect:/home";
    }



    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        model.addAttribute("captchaSettings", captchaSettings);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("g-recaptcha-response") String captchaResponse, @ModelAttribute("registerForm") @Valid RegisterForm registerForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (!captchaService.verifyCaptcha(captchaResponse)) {
            model.addAttribute("captchaError", "Invalid Captcha");
            return "register";
        }
        userService.registerNewUser(registerForm);
        return "redirect:/login";
    }
}
