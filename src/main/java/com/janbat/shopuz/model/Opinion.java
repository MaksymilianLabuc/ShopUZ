package com.janbat.shopuz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

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

    public Long getId() {
        return id;
    }

    public int getOcena() {
        return ocena;
    }

    public String getOpis() {
        return opis;
    }

    public String getUzytkownik() {
        return uzytkownik;
    }

    public ProductListing getProduct() {
        return product;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setUzytkownik(String uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public void setProduct(ProductListing product) {
        this.product = product;
    }
}
