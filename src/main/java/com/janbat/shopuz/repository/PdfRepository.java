package com.janbat.shopuz.repository;

import com.janbat.shopuz.model.Pdf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdfRepository extends JpaRepository<Pdf, Long> {
}


