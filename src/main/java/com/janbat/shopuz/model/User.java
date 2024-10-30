package com.janbat.shopuz.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


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

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(@NotBlank(message = "Nazwa użytkownika jest wymagana") String username) {
        this.username = username;
    }

    public void setPassword(@NotBlank(message = "Hasło jest wymagane") @Size(min = 6, message = "Hasło musi mieć co najmniej 6 znaków") String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public @NotBlank(message = "Nazwa użytkownika jest wymagana") String getUsername() {
        return username;
    }

    public @NotBlank(message = "Hasło jest wymagane") @Size(min = 6, message = "Hasło musi mieć co najmniej 6 znaków") String getPassword() {
        return password;
    }
}

