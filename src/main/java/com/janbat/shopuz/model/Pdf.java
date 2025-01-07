package com.janbat.shopuz.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pdfs")
public class Pdf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Minimalne pole – np. nazwa dokumentu

    @OneToMany(mappedBy = "pdf", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PdfItem> items = new ArrayList<>();

    // Konstruktory
    public Pdf() {
    }

    public Pdf(String name) {
        this.name = name;
    }

    // Gettery, settery
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PdfItem> getItems() {
        return items;
    }

    public void addItem(PdfItem item) {
        items.add(item);
        item.setPdf(this);
    }
}



