/**
 * @file OpinionRepository.java
 * @brief Interfejs OpinionRepository do zarządzania operacjami na opiniach.
 *
 * Ten interfejs definiuje metody do zarządzania operacjami na opiniach w bazie danych.
 */

package com.janbat.shopuz.repository;

import com.janbat.shopuz.model.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @brief Interfejs OpinionRepository do zarządzania operacjami na opiniach.
 */
@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    /**
     * @brief Znajduje opinie na podstawie identyfikatora produktu.
     * @param productId Identyfikator produktu.
     * @return Lista opinii o produkcie.
     */
    List<Opinion> findByProductId(Long productId);

    /**
     * @brief Liczy opinie na podstawie identyfikatora produktu.
     * @param productId Identyfikator produktu.
     * @return Liczba opinii o produkcie.
     */
    int countByProductId(Long productId);
}
