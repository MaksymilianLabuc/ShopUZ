package com.janbat.shopuz.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pdf_items")
public class PdfItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content; // Minimalne pole – np. treść danej pozycji

    @ManyToOne
    @JoinColumn(name = "pdf_id")
    private Pdf pdf;

    // Konstruktory
    public PdfItem() {
    }

    public PdfItem(String content) {
        this.content = content;
    }

    // Gettery, settery
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Pdf getPdf() {
        return pdf;
    }

    public void setPdf(Pdf pdf) {
        this.pdf = pdf;
    }
}



