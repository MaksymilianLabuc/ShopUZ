/**
 * @file DiscountCode.java
 * @brief Klasa DiscountCode reprezentująca kod rabatowy.
 *
 * Ta klasa zawiera informacje o kodzie rabatowym, w tym identyfikator, nazwę kodu oraz procent rabatu.
 */

package com.janbat.shopuz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * @brief Klasa DiscountCode reprezentująca kod rabatowy.
 */
@Entity
public class DiscountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeName;
    private int discountPercentage;

    /**
     * @brief Pobiera identyfikator kodu rabatowego.
     * @return Identyfikator kodu rabatowego.
     */
    public Long getId() {
        return id;
    }

    /**
     * @brief Pobiera nazwę kodu rabatowego.
     * @return Nazwa kodu rabatowego.
     */
    public String getCodeName() {
        return codeName;
    }

    /**
     * @brief Pobiera procent rabatu.
     * @return Procent rabatu.
     */
    public int getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * @brief Ustawia identyfikator kodu rabatowego.
     * @param id Identyfikator kodu rabatowego.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @brief Ustawia nazwę kodu rabatowego.
     * @param codeName Nazwa kodu rabatowego.
     */
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    /**
     * @brief Ustawia procent rabatu.
     * @param discountPercentage Procent rabatu.
     */
    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
