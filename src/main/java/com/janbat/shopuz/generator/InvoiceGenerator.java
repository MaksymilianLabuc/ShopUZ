package com.janbat.shopuz.generator;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.janbat.shopuz.model.Cart;
import com.janbat.shopuz.model.CartItem;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InvoiceGenerator {

    public void generateInvoice(String dest, Cart cart, BigDecimal discountedCost, String username, Date date) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Dodaj tytuł
        document.add(new Paragraph("Invoice"));

        // Dodaj nazwę użytkownika i datę
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        document.add(new Paragraph("User: " + username));
        document.add(new Paragraph("Date: " + dateFormat.format(date)));

        // Dodaj tabelę z produktami
        Table table = new Table(4);
        table.addCell("Product");
        table.addCell("Quantity");
        table.addCell("Price per unit");
        table.addCell("Total price");

        for (CartItem item : cart.getItems()) {
            table.addCell(item.getProduct().getName());
            table.addCell(String.valueOf(item.getQuantity()));
            table.addCell(String.valueOf(item.getProduct().getPrice()));
            table.addCell(String.valueOf(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))));
        }

        document.add(table);

        // Dodaj sumę kosztów
        BigDecimal totalCost = cart.getItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        document.add(new Paragraph("Total price: " + totalCost.toString() + " PLN"));

        // Dodaj cenę po rabacie
        document.add(new Paragraph("Total price after the discount: " + discountedCost.toString() + " PLN"));

        document.close();
    }
}
