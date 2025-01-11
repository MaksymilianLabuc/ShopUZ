/**
 * @file Cart.java
 * @brief Klasa Cart reprezentująca koszyk zakupowy.
 *
 * Ta klasa zawiera informacje o koszyku zakupowym, w tym identyfikator, nazwę użytkownika oraz listę produktów w koszyku.
 */

package com.janbat.shopuz.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief Klasa Cart reprezentująca koszyk zakupowy.
 */
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    /**
     * @brief Pobiera identyfikator koszyka.
     * @return Identyfikator koszyka.
     */
    public Long getId() {
        return id;
    }

    /**
     * @brief Pobiera nazwę użytkownika.
     * @return Nazwa użytkownika.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @brief Pobiera listę produktów w koszyku.
     * @return Lista produktów w koszyku.
     */
    public List<CartItem> getItems() {
        return items;
    }

    /**
     * @brief Ustawia identyfikator koszyka.
     * @param id Identyfikator koszyka.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @brief Ustawia nazwę użytkownika.
     * @param username Nazwa użytkownika.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @brief Ustawia listę produktów w koszyku.
     * @param items Lista produktów w koszyku.
     */
    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
