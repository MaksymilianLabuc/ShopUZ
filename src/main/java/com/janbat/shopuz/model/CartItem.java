/**
 * @file CartItem.java
 * @brief Klasa CartItem reprezentująca element koszyka zakupowego.
 *
 * Ta klasa zawiera informacje o elemencie koszyka zakupowego, w tym identyfikator, koszyk, produkt oraz ilość.
 */

package com.janbat.shopuz.model;

import jakarta.persistence.*;

/**
 * @brief Klasa CartItem reprezentująca element koszyka zakupowego.
 */
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductListing product;

    private int quantity;

    /**
     * @brief Pobiera koszyk, do którego należy element.
     * @return Koszyk, do którego należy element.
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * @brief Ustawia koszyk, do którego należy element.
     * @param cart Koszyk, do którego należy element.
     */
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /**
     * @brief Pobiera identyfikator elementu koszyka.
     * @return Identyfikator elementu koszyka.
     */
    public Long getId() {
        return id;
    }

    /**
     * @brief Pobiera produkt w elemencie koszyka.
     * @return Produkt w elemencie koszyka.
     */
    public ProductListing getProduct() {
        return product;
    }

    /**
     * @brief Pobiera ilość produktu w elemencie koszyka.
     * @return Ilość produktu w elemencie koszyka.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @brief Ustawia identyfikator elementu koszyka.
     * @param id Identyfikator elementu koszyka.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @brief Ustawia produkt w elemencie koszyka.
     * @param product Produkt w elemencie koszyka.
     */
    public void setProduct(ProductListing product) {
        this.product = product;
    }

    /**
     * @brief Ustawia ilość produktu w elemencie koszyka.
     * @param quantity Ilość produktu w elemencie koszyka.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
