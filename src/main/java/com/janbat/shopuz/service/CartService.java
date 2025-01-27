/**
 * @file CartService.java
 * @brief Klasa CartService do zarządzania operacjami na koszykach.
 *
 * Ta klasa zawiera metody do zarządzania operacjami na koszykach, w tym dodawanie i usuwanie przedmiotów oraz pobieranie koszyków użytkowników.
 */

package com.janbat.shopuz.service;

import com.janbat.shopuz.model.Cart;
import com.janbat.shopuz.model.CartItem;
import com.janbat.shopuz.model.ProductListing;
import com.janbat.shopuz.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief Klasa CartService do zarządzania operacjami na koszykach.
 */
@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    /**
     * @brief Pobiera koszyk użytkownika na podstawie nazwy użytkownika.
     * @param username Nazwa użytkownika.
     * @return Koszyk użytkownika.
     */
    public Cart getCartByUsername(String username) {
        return cartRepository.findByUsername(username);
    }

    /**
     * @brief Dodaje produkt do koszyka użytkownika.
     * @param username Nazwa użytkownika.
     * @param product Produkt do dodania.
     */
    public void addItemToCart(String username, ProductListing product) {
        Cart cart = cartRepository.findByUsername(username);
        if (cart == null) {
            cart = new Cart();
            cart.setUsername(username);
            cart.setItems(new ArrayList<>()); // Inicjalizacja listy przedmiotów
            System.out.println("Nowy koszyk utworzony dla użytkownika: " + username); // Debugowanie
        } else {
            System.out.println("Znaleziono istniejący koszyk dla użytkownika: " + username); // Debugowanie
        }
        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(1); // Domyślna ilość
        item.setCart(cart); // Ustawienie koszyka dla przedmiotu
        cart.getItems().add(item);
        System.out.println("Przedmiot dodany do koszyka: " + product.getName() + " (Ilość: " + item.getQuantity() + ")"); // Debugowanie
        cartRepository.save(cart);
        System.out.println("Koszyk zapisany dla użytkownika: " + username); // Debugowanie
    }

    /**
     * @brief Usuwa przedmiot z koszyka użytkownika.
     * @param username Nazwa użytkownika.
     * @param productId Identyfikator produktu do usunięcia.
     */
    @Transactional
    public void removeItemFromCart(String username, Long productId) {
        Cart cart = cartRepository.findByUsername(username);
        if (cart != null) {
            System.out.println("Koszyk znaleziony dla użytkownika: " + username); // Debugowanie
            CartItem itemToRemove = null;
            for (CartItem item : cart.getItems()) {
                System.out.println("Sprawdzanie przedmiotu w koszyku: " + item.getProduct().getId()); // Debugowanie
                System.out.println("Product ID: " +productId); // Debugowanie
                if (item.getProduct().getId().equals(productId)) {
                    itemToRemove = item;
                    break;
                }
            }
            if (itemToRemove != null) {
                cart.getItems().remove(itemToRemove);
                System.out.println("Przedmiot usunięty z koszyka: " + productId + " dla użytkownika: " + username); // Debugowanie
            } else {
                System.out.println("Przedmiot nie znaleziony w koszyku: " + productId + " dla użytkownika: " + username); // Debugowanie
            }
            cartRepository.save(cart);
        } else {
            System.out.println("Koszyk nie znaleziony dla użytkownika: " + username); // Debugowanie
        }
    }

    /**
     * @brief Pobiera wszystkie produkty znajdujące się w koszykach.
     * @return Lista produktów znajdujących się w koszykach.
     */
    public List<ProductListing> getAllProductsInCarts() {
        List<Cart> carts = cartRepository.findAll();
        List<ProductListing> productsInCarts = new ArrayList<>();
        for (Cart cart : carts) {
            for (CartItem item : cart.getItems()) {
                productsInCarts.add(item.getProduct());
            }
        }
        return productsInCarts;
    }
}
