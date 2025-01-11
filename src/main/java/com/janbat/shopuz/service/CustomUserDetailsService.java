/**
 * @file CustomUserDetailsService.java
 * @brief Klasa CustomUserDetailsService do zarządzania szczegółami użytkowników.
 *
 * Ta klasa implementuje interfejs UserDetailsService i zawiera metody do ładowania szczegółów użytkowników na podstawie nazwy użytkownika.
 */

package com.janbat.shopuz.service;

import com.janbat.shopuz.model.User;
import com.janbat.shopuz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @brief Klasa CustomUserDetailsService do zarządzania szczegółami użytkowników.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @brief Ładuje szczegóły użytkownika na podstawie nazwy użytkownika.
     *
     * @param username Nazwa użytkownika.
     * @return Szczegóły użytkownika.
     * @throws UsernameNotFoundException Jeśli użytkownik nie zostanie znaleziony.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("User not found: " + username);
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("Loaded User: " + user.getUsername());
        System.out.println("Stored Password: " + user.getPassword()); // Dodaj logowanie
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
