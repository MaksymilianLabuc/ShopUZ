package com.janbat.shopuz.service;

import com.janbat.shopuz.model.User;
import com.janbat.shopuz.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Dodajemy adnotację do inicjalizacji Mockito
public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks // Używamy @InjectMocks do wstrzykiwania mocka
    private CustomUserDetailsService userDetailsService;

    private User existingUser;
    private String existingUsername;

    @BeforeEach
    public void setUp() {
        existingUsername = "testuser";
        existingUser = new User();
        existingUser.setUsername(existingUsername);
        existingUser.setPassword("password"); // Hasło musi być ustawione
    }

    @Test
    void loadUserByUsername_existingUser_bezOptional() {
        when(userRepository.findByUsername(existingUsername)).thenReturn(existingUser);

        UserDetails userDetails = userDetailsService.loadUserByUsername(existingUsername);

        assertNotNull(userDetails, "UserDetails nie powinien być null");
        assertEquals(existingUsername, userDetails.getUsername(), "Nazwa użytkownika powinna się zgadzać");
        verify(userRepository).findByUsername(existingUsername);
    }

    @Test
    void loadUserByUsername_notFound() {
        when(userRepository.findByUsername(existingUsername)).thenReturn(null); // Zwracamy null

        assertThrows(UsernameNotFoundException.class, () -> {
            try{
                userDetailsService.loadUserByUsername(existingUsername);
            }catch (UsernameNotFoundException e){
                assertEquals("User not found", e.getMessage());
                throw e;
            }
        });

        verify(userRepository).findByUsername(existingUsername);
    }
}