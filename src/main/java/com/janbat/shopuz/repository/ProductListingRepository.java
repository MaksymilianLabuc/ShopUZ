/**
 * @file ProductListingRepository.java
 * @brief Interfejs ProductListingRepository do zarządzania operacjami na ofertach produktów.
 *
 * Ten interfejs definiuje metody do zarządzania operacjami na ofertach produktów w bazie danych.
 */

package com.janbat.shopuz.repository;

import com.janbat.shopuz.model.ProductListing;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @brief Interfejs ProductListingRepository do zarządzania operacjami na ofertach produktów.
 */
public interface ProductListingRepository extends JpaRepository<ProductListing, Long> {
}
