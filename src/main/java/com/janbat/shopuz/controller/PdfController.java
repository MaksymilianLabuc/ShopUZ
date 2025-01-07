package com.janbat.shopuz.controller;

import com.janbat.shopuz.service.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pdfs")
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    // Endpoint: GET /pdfs/{id} -> generuje PDF i zwraca w odpowiedzi
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getPdf(@PathVariable Long id) {
        byte[] pdfBytes = pdfService.generatePdf(id);

        HttpHeaders headers = new HttpHeaders();
        // "attachment" zamiast "inline" – aby wymusić pobieranie
        // headers.add("Content-Disposition", "attachment; filename=dokument.pdf");

        // "inline" – aby wyświetlić w przeglądarce
        headers.add("Content-Disposition", "inline; filename=dokument.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}


