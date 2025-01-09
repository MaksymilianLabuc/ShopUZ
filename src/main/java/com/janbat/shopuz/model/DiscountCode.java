package com.janbat.shopuz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DiscountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeName;
    private int discountPercentage;

    public Long getId() {
        return id;
    }

    public String getCodeName() {
        return codeName;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
