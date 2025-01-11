/**
 * @file User.java
 * @brief Klasa User reprezentująca użytkownika.
 *
 * Ta klasa zawiera informacje o użytkowniku, w tym identyfikator, nazwę użytkownika oraz hasło.
 */

package com.janbat.shopuz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @brief Klasa User reprezentująca użytkownika.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nazwa użytkownika jest wymagana")
    @Column(unique=true)
    private String username;

    @NotBlank(message = "Hasło jest wymagane")
    @Size(min = 6, message = "Hasło musi mieć co najmniej 6 znaków")
    private String password;

    /**
     * @brief Ustawia identyfikator użytkownika.
     * @param id Identyfikator użytkownika.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @brief Ustawia nazwę użytkownika.
     * @param username Nazwa użytkownika.
     */
    public void setUsername(@NotBlank(message = "Nazwa użytkownika jest wymagana") String username) {
        this.username = username;
    }

    /**
     * @brief Ustawia hasło użytkownika.
     * @param password Hasło użytkownika.
     */
    public void setPassword(@NotBlank(message = "Hasło jest wymagane") @Size(min = 6, message = "Hasło musi mieć co najmniej 6 znaków") String password) {
        this.password = password;
    }

    /**
     * @brief Pobiera identyfikator użytkownika.
     * @return Identyfikator użytkownika.
     */
    public Long getId() {
        return id;
    }

    /**
     * @brief Pobiera nazwę użytkownika.
     * @return Nazwa użytkownika.
     */
    public @NotBlank(message = "Nazwa użytkownika jest wymagana") String getUsername() {
        return username;
    }

    /**
     * @brief Pobiera hasło użytkownika.
     * @return Hasło użytkownika.
     */
    public @NotBlank(message = "Hasło jest wymagane") @Size(min = 6, message = "Hasło musi mieć co najmniej 6 znaków") String getPassword() {
        return password;
    }
}
