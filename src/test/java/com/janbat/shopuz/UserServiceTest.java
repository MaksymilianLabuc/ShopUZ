package com.janbat.shopuz;

import com.janbat.shopuz.model.RegisterForm;
import com.janbat.shopuz.model.User;
import com.janbat.shopuz.repository.UserRepository;
import com.janbat.shopuz.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void registerNewUser_shouldRegisterUserWithHashedPassword() {
        // Testuje rejestrację nowego użytkownika. Sprawdza, czy hasło jest haszowane przed zapisem.
        RegisterForm registerForm = new RegisterForm();
        registerForm.setUsername("testuser");
        registerForm.setPassword("password123");

        String hashedPassword = "hashedPassword"; // Mockowane zahaszowane hasło
        when(bCryptPasswordEncoder.encode(registerForm.getPassword())).thenReturn(hashedPassword);

        userService.registerNewUser(registerForm);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class); // przechwytywanie argumentu
        verify(userRepository).save(userCaptor.capture()); // Weryfikuje, czy metoda save została wywołana.

        User savedUser = userCaptor.getValue(); // Pobranie przechwyconego użytkownika
        assertEquals(registerForm.getUsername(), savedUser.getUsername(), "Nazwa użytkownika powinna być zgodna");
        assertEquals(hashedPassword, savedUser.getPassword(), "Hasło powinno być zahaszowane");
    }


}