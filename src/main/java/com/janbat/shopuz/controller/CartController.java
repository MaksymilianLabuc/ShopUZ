package com.janbat.shopuz.controller;

import com.janbat.shopuz.model.Cart;
import com.janbat.shopuz.model.DiscountCode;
import com.janbat.shopuz.model.ProductListing;
import com.janbat.shopuz.service.CartService;
import com.janbat.shopuz.service.DiscountCodeService;
import com.janbat.shopuz.service.ProductListingService;
import com.janbat.shopuz.generator.InvoiceGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    @Autowired
    private CartService cartService;
    @Autowired
    private DiscountCodeService discountCodeService;
    @Autowired
    private ProductListingService productListingService;
    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/cart")
    public String viewCart(Model model, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            return "redirect:/login"; // Przekierowanie do logowania, jeśli użytkownik nie jest zalogowany
        }
        List<DiscountCode> codes = discountCodeService.findAll();
        model.addAttribute("codes", codes);
        Cart cart = cartService.getCartByUsername(username);
        if (cart == null) {
            cart = new Cart();
            cart.setUsername(username);
            cart.setItems(new ArrayList<>()); // Inicjalizacja listy przedmiotów
        }
        model.addAttribute("cart", cart);
        model.addAttribute("username", username); // Dodanie nazwy użytkownika do modelu
        return "cart";
    }

    @PostMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable Long productId, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            return "redirect:/login"; // Przekierowanie do logowania, jeśli użytkownik nie jest zalogowany
        }
        ProductListing product = productListingService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        cartService.addItemToCart(username, product);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeItemFromCart(@PathVariable Long id, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        if (username != null) {
            System.out.println("Usuwanie przedmiotu z koszyka: " + id + " dla użytkownika: " + username); // Debugowanie
            cartService.removeItemFromCart(username, id);
        }
        return "redirect:/cart";
    }
    @PostMapping("/cart/apply-discount")
    @ResponseBody
    public String applyDiscount(@RequestParam String discountCode, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            return "redirect:/login"; // Przekierowanie do logowania, jeśli użytkownik nie jest zalogowany
        }
        DiscountCode code = discountCodeService.findByCodeName(discountCode);
        if (code == null) {
            return "Kod rabatowy nie istnieje";
        }
        Cart cart = cartService.getCartByUsername(username);
        if (cart == null) {
            return "Koszyk jest pusty";
        }
        double discount = code.getDiscountPercentage() / 100.0;
        BigDecimal totalCost = cart.getItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal discountedCost = totalCost.multiply(BigDecimal.valueOf(1 - discount));
        return String.format("Cena po rabacie: %.2f PLN", discountedCost.doubleValue());
    }

        @PostMapping("/cart/generate-invoice")
        public String generateInvoice(HttpServletRequest request) {
            String username = (String) request.getSession().getAttribute("username");
            if (username == null) {
                return "redirect:/login"; // Przekierowanie do logowania, jeśli użytkownik nie jest zalogowany
            }
            Cart cart = cartService.getCartByUsername(username);
            if (cart == null || cart.getItems().isEmpty()) {
                return "redirect:/cart"; // Przekierowanie do koszyka, jeśli jest pusty
            }
            // Obliczanie ceny po rabacie
            String discountCode = request.getParameter("discountCode");
            DiscountCode code = discountCodeService.findByCodeName(discountCode);
            double discount = code != null ? code.getDiscountPercentage() / 100.0 : 0.0;
            BigDecimal totalCost = cart.getItems().stream()
                    .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal discountedCost = totalCost.multiply(BigDecimal.valueOf(1 - discount));

            InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
            try {
                invoiceGenerator.generateInvoice("invoice.pdf", cart, discountedCost, username, new Date());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "redirect:/cart?error=invoiceGenerationFailed";
            }

            // Wysyłanie powiadomienia e-mail
            String email = request.getParameter("email");
            sendEmailNotification(email, "Invoice Generated", "Your invoice has been generated and is attached.");

            return "redirect:/cart?success=invoiceGenerated";
        }

    private void sendEmailNotification(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("shopuzproject@interia.pl"); // Ustaw adres nadawcy
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
            mailSender.send(message);
            logger.info("E-mail wysłany do: " + to);
        } catch (Exception e) {
            logger.error("Błąd podczas wysyłania e-maila: ", e);
        }
    }


}
