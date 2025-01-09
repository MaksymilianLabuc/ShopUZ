package com.janbat.shopuz.controller;

import com.janbat.shopuz.model.DiscountCode;
import com.janbat.shopuz.service.DiscountCodeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DiscountCodeController {
    @Autowired
    private DiscountCodeService discountCodeService;

    @GetMapping("/code")
    public String viewDiscountCodes(HttpServletRequest request, Model model) {
        String username = (String) request.getSession().getAttribute("username");
        if (!"admin".equals(username)) {
            return "redirect:/login"; // Przekierowanie do logowania, jeśli użytkownik nie jest adminem
        }
        List<DiscountCode> codes = discountCodeService.findAll();
        model.addAttribute("codes", codes); // Dodanie kodów rabatowych do modelu
        model.addAttribute("username", username); // Dodanie nazwy użytkownika do modelu
        return "code";
    }

    @PostMapping("/code")
    public String addDiscountCode(@RequestParam String codeName, @RequestParam int discountPercentage, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        if (!"admin".equals(username)) {
            return "redirect:/login"; // Przekierowanie do logowania, jeśli użytkownik nie jest adminem
        }
        if (discountPercentage > 80) {
            return "redirect:/code?error=discountTooHigh"; // Przekierowanie z błędem, jeśli procent rabatu przekracza 80%
        }
        DiscountCode discountCode = new DiscountCode();
        discountCode.setCodeName(codeName);
        discountCode.setDiscountPercentage(discountPercentage);
        discountCodeService.save(discountCode);
        return "redirect:/code";
    }
}

