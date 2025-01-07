package com.janbat.shopuz.service;

import com.janbat.shopuz.model.Pdf;
import com.janbat.shopuz.model.PdfItem;
import com.janbat.shopuz.repository.PdfRepository;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {

    private final PdfRepository pdfRepository;

    // Konstruktor z wstrzykiwaniem zależności
    public PdfService(PdfRepository pdfRepository) {
        this.pdfRepository = pdfRepository;
    }

    public byte[] generatePdf(Long pdfId) {
        // Pobieranie obiektu PDF z bazy danych
        Pdf pdfEntity = pdfRepository.findById(pdfId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono PDF o ID: " + pdfId));

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            // Tworzenie dokumentu PDF
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Dodawanie nagłówka
            Paragraph header = new Paragraph("Dokument: " + pdfEntity.getName())
                    .setBold()
                    .setFontSize(16);
            document.add(header);

            // Pobieranie pozycji z PDF
            List<PdfItem> items = pdfEntity.getItems();

            if (!items.isEmpty()) {
                // Tworzenie tabeli z dwiema kolumnami: Lp. i Treść
                Table table = new Table(2);

                int counter = 1;
                for (PdfItem item : items) {
                    table.addCell(String.valueOf(counter));
                    table.addCell(item.getContent());
                    counter++;
                }

                // Dodawanie tabeli do dokumentu
                document.add(new Paragraph("\n"));
                document.add(table);
            } else {
                document.add(new Paragraph("\nBrak pozycji w tym dokumencie."));
            }

            // Zamknięcie dokumentu
            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Błąd podczas generowania PDF", e);
        }
    }
}





