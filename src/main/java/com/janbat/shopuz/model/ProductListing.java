/**
 * @file ProductListing.java
 * @brief Klasa ProductListing reprezentująca ofertę produktu.
 *
 * Ta klasa zawiera informacje o ofercie produktu, w tym identyfikator, nazwę, opis, cenę, ocenę, właściciela, liczbę opinii oraz listę opinii.
 */

package com.janbat.shopuz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.List;

/**
 * @brief Klasa ProductListing reprezentująca ofertę produktu.
 */
@Entity
public class ProductListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private int rating;
    private String owner;
    private int opinionCount; // Dodane pole na liczbę opinii

    @OneToMany(mappedBy = "product")
    private List<Opinion> opinions;

    /**
     * @brief Pobiera identyfikator oferty produktu.
     * @return Identyfikator oferty produktu.
     */
    public Long getId() {
        return id;
    }

    /**
     * @brief Ustawia identyfikator oferty produktu.
     * @param id Identyfikator oferty produktu.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @brief Pobiera nazwę produktu.
     * @return Nazwa produktu.
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Ustawia nazwę produktu.
     * @param name Nazwa produktu.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @brief Pobiera opis produktu.
     * @return Opis produktu.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @brief Ustawia opis produktu.
     * @param description Opis produktu.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @brief Pobiera cenę produktu.
     * @return Cena produktu.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @brief Ustawia cenę produktu.
     * @param price Cena produktu.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @brief Pobiera ocenę produktu.
     * @return Ocena produktu.
     */
    public int getRating() {
        return rating;
    }

    /**
     * @brief Ustawia ocenę produktu.
     * @param rating Ocena produktu.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @brief Pobiera właściciela produktu.
     * @return Właściciel produktu.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @brief Ustawia właściciela produktu.
     * @param owner Właściciel produktu.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @brief Pobiera liczbę opinii o produkcie.
     * @return Liczba opinii o produkcie.
     */
    public int getOpinionCount() {
        return opinionCount;
    }

    /**
     * @brief Ustawia liczbę opinii o produkcie.
     * @param opinionCount Liczba opinii o produkcie.
     */
    public void setOpinionCount(int opinionCount) {
        this.opinionCount = opinionCount;
    }

    /**
     * @brief Pobiera listę opinii o produkcie.
     * @return Lista opinii o produkcie.
     */
    public List<Opinion> getOpinions() {
        return opinions;
    }

    /**
     * @brief Ustawia listę opinii o produkcie.
     * @param opinions Lista opinii o produkcie.
     */
    public void setOpinions(List<Opinion> opinions) {
        this.opinions = opinions;
    }
}
