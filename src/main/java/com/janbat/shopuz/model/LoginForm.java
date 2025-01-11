/**
 * @file LoginForm.java
 * @brief Klasa LoginForm reprezentująca formularz logowania.
 *
 * Ta klasa zawiera informacje o formularzu logowania, w tym nazwę użytkownika i hasło.
 */

package com.janbat.shopuz.model;

import jakarta.validation.constraints.NotBlank;

/**
 * @brief Klasa LoginForm reprezentująca formularz logowania.
 */
public class LoginForm {
    @NotBlank(message = "Nazwa użytkownika jest wymagana")
    private String username;

    @NotBlank(message = "Hasło jest wymagane")
    private String password;

    /**
     * @brief Pobiera nazwę użytkownika.
     * @return Nazwa użytkownika.
     */
    public @NotBlank(message = "Nazwa użytkownika jest wymagana") String getUsername() {
        return username;
    }

    /**
     * @brief Pobiera hasło.
     * @return Hasło.
     */
    public @NotBlank(message = "Hasło jest wymagane") String getPassword() {
        return password;
    }

    /**
     * @brief Ustawia hasło.
     * @param password Hasło.
     */
    public void setPassword(@NotBlank(message = "Hasło jest wymagane") String password) {
        this.password = password;
    }

    /**
     * @brief Ustawia nazwę użytkownika.
     * @param username Nazwa użytkownika.
     */
    public void setUsername(@NotBlank(message = "Nazwa użytkownika jest wymagana") String username) {
        this.username = username;
    }
}
