/**
 * @file Opinion.java
 * @brief Klasa Opinion reprezentująca opinię o produkcie.
 *
 * Ta klasa zawiera informacje o opinii o produkcie, w tym identyfikator, ocenę, opis, użytkownika oraz produkt.
 */

package com.janbat.shopuz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * @brief Klasa Opinion reprezentująca opinię o produkcie.
 */
@Entity
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int ocena;
    private String opis;
    private String uzytkownik;

    @ManyToOne
    private ProductListing product;

    /**
     * @brief Pobiera identyfikator opinii.
     * @return Identyfikator opinii.
     */
    public Long getId() {
        return id;
    }

    /**
     * @brief Pobiera ocenę produktu.
     * @return Ocena produktu.
     */
    public int getOcena() {
        return ocena;
    }

    /**
     * @brief Pobiera opis opinii.
     * @return Opis opinii.
     */
    public String getOpis() {
        return opis;
    }

    /**
     * @brief Pobiera nazwę użytkownika, który dodał opinię.
     * @return Nazwa użytkownika, który dodał opinię.
     */
    public String getUzytkownik() {
        return uzytkownik;
    }

    /**
     * @brief Pobiera produkt, którego dotyczy opinia.
     * @return Produkt, którego dotyczy opinia.
     */
    public ProductListing getProduct() {
        return product;
    }

    /**
     * @brief Ustawia identyfikator opinii.
     * @param id Identyfikator opinii.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @brief Ustawia ocenę produktu.
     * @param ocena Ocena produktu.
     */
    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    /**
     * @brief Ustawia opis opinii.
     * @param opis Opis opinii.
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     * @brief Ustawia nazwę użytkownika, który dodał opinię.
     * @param uzytkownik Nazwa użytkownika, który dodał opinię.
     */
    public void setUzytkownik(String uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    /**
     * @brief Ustawia produkt, którego dotyczy opinia.
     * @param product Produkt, którego dotyczy opinia.
     */
    public void setProduct(ProductListing product) {
        this.product = product;
    }
}
