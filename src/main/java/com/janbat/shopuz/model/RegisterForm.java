/**
 * @file RegisterForm.java
 * @brief Klasa RegisterForm reprezentująca formularz rejestracji.
 *
 * Ta klasa zawiera informacje o formularzu rejestracji, w tym nazwę użytkownika i hasło.
 */

package com.janbat.shopuz.model;

import com.janbat.shopuz.validation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @brief Klasa RegisterForm reprezentująca formularz rejestracji.
 */
public class RegisterForm {
    @NotBlank(message = "Nazwa użytkownika jest wymagana")
    @UniqueUsername
    private String username;

    /**
     * @brief Ustawia nazwę użytkownika.
     * @param username Nazwa użytkownika.
     */
    public void setUsername(@NotBlank(message = "Nazwa użytkownika jest wymagana") String username) {
        this.username = username;
    }

    @Size(min = 6, message = "Hasło musi mieć co najmniej 6 znaków")
    @NotBlank(message = "Hasło jest wymagane")
    private String password;

    /**
     * @brief Ustawia hasło.
     * @param password Hasło.
     */
    public void setPassword(@Size(min = 6, message = "Hasło musi mieć co najmniej 6 znaków") @NotBlank(message = "Hasło jest wymagane") String password) {
        this.password = password;
    }

    /**
     * @brief Pobiera hasło.
     * @return Hasło.
     */
    public @Size(min = 6, message = "Hasło musi mieć co najmniej 6 znaków") @NotBlank(message = "Hasło jest wymagane") String getPassword() {
        return password;
    }

    /**
     * @brief Pobiera nazwę użytkownika.
     * @return Nazwa użytkownika.
     */
    public @NotBlank(message = "Nazwa użytkownika jest wymagana") String getUsername() {
        return username;
    }
}
