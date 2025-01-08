package com.janbat.shopuz.service;

import com.janbat.shopuz.model.Cart;
import com.janbat.shopuz.model.CartItem;
import com.janbat.shopuz.model.ProductListing;
import com.janbat.shopuz.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart getCartByUsername(String username) {
        return cartRepository.findByUsername(username);
    }

    public void addItemToCart(String username, ProductListing product) {
        Cart cart = cartRepository.findByUsername(username);
        if (cart == null) {
            cart = new Cart();
            cart.setUsername(username);
            cart.setItems(new ArrayList<>()); // Initialize the items list
            System.out.println("New cart created for user: " + username); // Debugging
        } else {
            System.out.println("Existing cart found for user: " + username); // Debugging
        }
        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(1); // Default quantity
        item.setCart(cart); // Set the cart for the item
        cart.getItems().add(item);
        System.out.println("Item added to cart: " + product.getName() + " (Quantity: " + item.getQuantity() + ")"); // Debugging
        cartRepository.save(cart);
        System.out.println("Cart saved for user: " + username); // Debugging
    }
}
