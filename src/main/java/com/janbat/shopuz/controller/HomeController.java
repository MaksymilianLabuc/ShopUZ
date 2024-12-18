package com.janbat.shopuz.controller;

import com.janbat.shopuz.model.ProductListing;
import com.janbat.shopuz.service.ProductListingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private ProductListingService productListingService;

    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        List<ProductListing> productListings = productListingService.getAllProductListings();
        model.addAttribute("products", productListings);

        // Uzyskanie nazwy użytkownika z sesji
        String username = (String) model.asMap().get("username");
        if (username == null) {
            username = (String) request.getSession().getAttribute("username");
        }
        if (username != null) {
            model.addAttribute("username", username);
            System.out.println("Username in HomeController: " + username); // Debugging
        } else {
            System.out.println("Username is null in HomeController"); // Debugging
        }
        return "home";
    }

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new ProductListing());
        return "addProduct";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute ProductListing product) {
        productListingService.saveProduct(product);
        return "redirect:/home";
    }
    @PostMapping("/rate-product/{id}/{rating}")
    public ResponseEntity<?> rateProduct(@PathVariable Long id, @PathVariable int rating) {
        ProductListing product = productListingService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        product.setRating(rating);
        productListingService.saveProduct(product);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Rating updated successfully");

        return ResponseEntity.ok(response);
    }

}
