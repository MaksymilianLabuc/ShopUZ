/**
 * @file DiscountCodeRepository.java
 * @brief Interfejs DiscountCodeRepository do zarządzania operacjami na kodach rabatowych.
 *
 * Ten interfejs definiuje metody do zarządzania operacjami na kodach rabatowych w bazie danych.
 */

package com.janbat.shopuz.repository;

import com.janbat.shopuz.model.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @brief Interfejs DiscountCodeRepository do zarządzania operacjami na kodach rabatowych.
 */
public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {
    /**
     * @brief Znajduje kod rabatowy na podstawie nazwy kodu.
     * @param codeName Nazwa kodu.
     * @return Kod rabatowy.
     */
    DiscountCode findByCodeName(String codeName);
}
