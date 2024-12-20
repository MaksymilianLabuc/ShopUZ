package com.janbat.shopuz.controller;

import com.janbat.shopuz.model.Opinion;
import com.janbat.shopuz.model.ProductListing;
import com.janbat.shopuz.service.OpinionService;
import com.janbat.shopuz.service.ProductListingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private ProductListingService productListingService;

    @Autowired
    private OpinionService opinionService;

    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        List<ProductListing> productListings = productListingService.getAllProductListings();
        // Dodanie liczby opinii dla każdego produktu
        for (ProductListing product : productListings) {
            int opinionCount = opinionService.countOpinionsByProductId(product.getId());
            product.setOpinionCount(opinionCount);
        }
        model.addAttribute("products", productListings);

        // Uzyskanie nazwy użytkownika z sesji
        String username = (String) request.getSession().getAttribute("username");
        if (username != null) {
            model.addAttribute("username", username);
            System.out.println("Username in HomeController: " + username); // Debugging
        } else {
            System.out.println("Username is null in HomeController"); // Debugging
        }

        return "home";
    }

    @GetMapping("/add-product")
    public String showAddProductForm(Model model, HttpServletRequest request) {
        ProductListing product = new ProductListing();
        String username = (String) request.getSession().getAttribute("username");
        if (username != null) {
            product.setOwner(username); // Przypisanie właściciela produktu
            model.addAttribute("username", username);
            System.out.println("Username in ProductController: " + username); // Debugging
        } else {
            System.out.println("Username is null in ProductController"); // Debugging
        }
        model.addAttribute("product", product);
        return "addProduct";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute ProductListing product, HttpServletRequest request, Model model) {
        // Uzyskanie nazwy użytkownika z sesji
        String username = (String) request.getSession().getAttribute("username");
        if (username == null) {
            // Jeśli użytkownik jest null, dodaj komunikat o błędzie do modelu i przekieruj z powrotem do formularza
            model.addAttribute("error", "You must be logged in to add a product.");
            return "addProduct";
        }
        product.setOwner(username); // Przypisanie właściciela produktu

        // Walidacja pól i ustawienie domyślnych wartości, jeśli są puste
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            product.setName("brak");
        }
        if (product.getDescription() == null || product.getDescription().trim().isEmpty()) {
            product.setDescription("brak");
        }
        if (product.getPrice() == null) {
            product.setPrice(BigDecimal.ZERO);
        }

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

    @GetMapping("/product/{id}")
    public String showProductDetails(@PathVariable Long id, Model model, HttpServletRequest request) {
        ProductListing product = productListingService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        // Dodanie liczby opinii do modelu
        int opinionCount = opinionService.countOpinionsByProductId(id);
        model.addAttribute("opinionCount", opinionCount);
        model.addAttribute("product", product);

        // Uzyskanie nazwy użytkownika z sesji
        String username = (String) request.getSession().getAttribute("username");
        if (username != null) {
            model.addAttribute("username", username);
            System.out.println("Username in HomeController: " + username); // Debugging
        } else {
            System.out.println("Username is null in HomeController"); // Debugging
        }

        // Pobranie opinii dla produktu
        List<Opinion> opinions = opinionService.getOpinionsByProductId(id);
        model.addAttribute("opinions", opinions);


        // Dodanie nowego obiektu Opinion do modelu
        model.addAttribute("opinion", new Opinion());

        return "productDetails"; // Nazwa szablonu HTML
    }

    @PostMapping("/add-opinion/{productId}")
    public String addOpinion(@PathVariable Long productId, @ModelAttribute Opinion opinion, HttpServletRequest request) {
        ProductListing product = productListingService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        opinion.setProduct(product);
        opinion.setUzytkownik((String) request.getSession().getAttribute("username"));
        opinionService.saveOpinion(opinion);

        // Oblicz średnią ocen i zaktualizuj pole oceny w modelu produktu
        double averageRating = opinionService.calculateAverageRating(productId);
        product.setRating((int) Math.round(averageRating));
        productListingService.saveProduct(product);
        return "redirect:/product/" + productId;
    }
}
