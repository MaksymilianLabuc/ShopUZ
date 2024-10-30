package com.janbat.shopuz.service;

import com.janbat.shopuz.model.RegisterForm;
import com.janbat.shopuz.model.User;
import com.janbat.shopuz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void registerNewUser(RegisterForm registerForm) {
        User user = new User();
        user.setUsername(registerForm.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registerForm.getPassword()));
        userRepository.save(user);
    }
}
