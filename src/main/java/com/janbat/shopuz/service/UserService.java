/**
 * @file UserService.java
 * @brief Klasa UserService do zarządzania operacjami na użytkownikach.
 *
 * Ta klasa zawiera metody do zarządzania operacjami na użytkownikach, w tym rejestrację nowych użytkowników.
 */

package com.janbat.shopuz.service;

import com.janbat.shopuz.model.RegisterForm;
import com.janbat.shopuz.model.User;
import com.janbat.shopuz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @brief Klasa UserService do zarządzania operacjami na użytkownikach.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * @brief Konstruktor klasy UserService.
     * @param userRepository Repozytorium użytkowników.
     * @param bCryptPasswordEncoder Enkoder haseł BCrypt.
     */
    @Autowired
    public UserService(UserRepository userRepository, @Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * @brief Rejestruje nowego użytkownika.
     * @param registerForm Formularz rejestracji zawierający dane użytkownika.
     */
    public void registerNewUser(RegisterForm registerForm) {
        User user = new User();
        user.setUsername(registerForm.getUsername());
        String hashedPassword = bCryptPasswordEncoder.encode(registerForm.getPassword());
        System.out.println("Hashed Password: " + hashedPassword); // Dodaj logowanie
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

}
