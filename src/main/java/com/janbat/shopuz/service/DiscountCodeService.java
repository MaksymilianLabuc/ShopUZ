/**
 * @file DiscountCodeService.java
 * @brief Klasa DiscountCodeService do zarządzania operacjami na kodach rabatowych.
 *
 * Ta klasa zawiera metody do zarządzania operacjami na kodach rabatowych, w tym zapisywanie, wyszukiwanie i pobieranie wszystkich kodów rabatowych.
 */

package com.janbat.shopuz.service;

import com.janbat.shopuz.model.DiscountCode;
import com.janbat.shopuz.repository.DiscountCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @brief Klasa DiscountCodeService do zarządzania operacjami na kodach rabatowych.
 */
@Service
public class DiscountCodeService {
    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    /**
     * @brief Zapisuje kod rabatowy.
     * @param discountCode Kod rabatowy do zapisania.
     */
    public void save(DiscountCode discountCode) {
        discountCodeRepository.save(discountCode);
    }

    /**
     * @brief Pobiera wszystkie kody rabatowe.
     * @return Lista wszystkich kodów rabatowych.
     */
    public List<DiscountCode> findAll() {
        return discountCodeRepository.findAll();
    }

    /**
     * @brief Znajduje kod rabatowy na podstawie nazwy kodu.
     * @param codeName Nazwa kodu.
     * @return Kod rabatowy.
     */
    public DiscountCode findByCodeName(String codeName) {
        return discountCodeRepository.findByCodeName(codeName);
    }
}
