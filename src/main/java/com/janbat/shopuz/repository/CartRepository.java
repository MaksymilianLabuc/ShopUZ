/**
 * @file CartRepository.java
 * @brief Interfejs CartRepository do zarządzania operacjami na koszykach.
 *
 * Ten interfejs definiuje metody do zarządzania operacjami na koszykach w bazie danych.
 */

package com.janbat.shopuz.repository;

import com.janbat.shopuz.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @brief Interfejs CartRepository do zarządzania operacjami na koszykach.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    /**
     * @brief Znajduje koszyk na podstawie nazwy użytkownika.
     * @param username Nazwa użytkownika.
     * @return Koszyk użytkownika.
     */
    Cart findByUsername(String username);
}
