package com.janbat.shopuz.controller;

import com.janbat.shopuz.model.Cart;
import com.janbat.shopuz.model.ProductListing;
import com.janbat.shopuz.service.CartService;
import com.janbat.shopuz.service.ProductListingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductListingService productListingService;

    @GetMapping("/cart")
    public String viewCart(Model model, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            return "redirect:/login"; // Redirect to login if the user is not logged in
        }
        Cart cart = cartService.getCartByUsername(username);
        if (cart == null) {
            cart = new Cart();
            cart.setUsername(username);
            cart.setItems(new ArrayList<>()); // Initialize the items list
        }
        model.addAttribute("cart", cart);
        model.addAttribute("username", username); // Add username to the model
        return "cart";
    }

    @PostMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable Long productId, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            return "redirect:/login"; // Redirect to login if the user is not logged in
        }
        ProductListing product = productListingService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        cartService.addItemToCart(username, product);
        return "redirect:/cart";
    }
}
