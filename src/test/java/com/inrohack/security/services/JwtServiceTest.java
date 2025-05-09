package com.inrohack.security.services;

import com.inrohack.security.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("Generar un token")
    void generateToken() {
        String generatedToken= jwtService.generateToken("John", "[ROLE_ADMIN]");
        System.out.println("token created: " + generatedToken);
    }

    @Test
    void validateToken() {
    }

    @Test
    void extractUsername() {
    }

    @Test
    void extractRoles() {
    }
}