package com.janbat.shopuz.model;

import com.janbat.shopuz.validation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterForm {
    @NotBlank(message = "Nazwa użytkownika jest wymagana")
    @UniqueUsername
    private String username;

    public void setUsername(@NotBlank(message = "Nazwa użytkownika jest wymagana") String username) {
        this.username = username;
    }

    public void setPassword(@Size(min = 6, message = "Hasło musi mieć co najmniej 6 znaków") @NotBlank(message = "Hasło jest wymagane") String password) {
        this.password = password;
    }

    public @Size(min = 6, message = "Hasło musi mieć co najmniej 6 znaków") @NotBlank(message = "Hasło jest wymagane") String getPassword() {
        return password;
    }

    public @NotBlank(message = "Nazwa użytkownika jest wymagana") String getUsername() {
        return username;
    }

    @Size(min = 6, message = "Hasło musi mieć co najmniej 6 znaków")
    @NotBlank(message = "Hasło jest wymagane")
    private String password;

    // Gettery i settery
}

