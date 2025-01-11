/**
 * @file UserRepository.java
 * @brief Interfejs UserRepository do zarządzania operacjami na użytkownikach.
 *
 * Ten interfejs definiuje metody do zarządzania operacjami na użytkownikach w bazie danych.
 */

package com.janbat.shopuz.repository;

import com.janbat.shopuz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @brief Interfejs UserRepository do zarządzania operacjami na użytkownikach.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * @brief Znajduje użytkownika na podstawie nazwy użytkownika.
     * @param username Nazwa użytkownika.
     * @return Użytkownik.
     */
    User findByUsername(String username);

    /**
     * @brief Sprawdza, czy użytkownik istnieje na podstawie nazwy użytkownika.
     * @param username Nazwa użytkownika.
     * @return True, jeśli użytkownik istnieje, w przeciwnym razie false.
     */
    boolean existsByUsername(String username);
}
