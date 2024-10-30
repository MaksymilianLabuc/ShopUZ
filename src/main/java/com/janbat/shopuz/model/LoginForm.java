package com.janbat.shopuz.model;


import jakarta.validation.constraints.NotBlank;

public class LoginForm {
    @NotBlank(message = "Nazwa użytkownika jest wymagana")
    private String username;

    @NotBlank(message = "Hasło jest wymagane")
    private String password;

    public @NotBlank(message = "Nazwa użytkownika jest wymagana") String getUsername() {
        return username;
    }

    public @NotBlank(message = "Hasło jest wymagane") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Hasło jest wymagane") String password) {
        this.password = password;
    }

    public void setUsername(@NotBlank(message = "Nazwa użytkownika jest wymagana") String username) {
        this.username = username;
    }
}

