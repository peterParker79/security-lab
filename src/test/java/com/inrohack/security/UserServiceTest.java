package com.inrohack.security;

import com.inrohack.security.models.User;
import com.inrohack.security.repositories.UserRepository;
import com.inrohack.security.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class UserServiceTest {

    User user;
    @Autowired
     UserService userService; //userService ya usa User repository

    @Autowired
    UserRepository userRepository;

/*
    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("testing");
        user.setPassword("1234");
        System.out.println("El usuario inicial es: " + user);


        userRepository.save(user);
    }
    */

    /*
    @AfterEach
    public void cleanUp() {
        userRepository.delete(user);
    }
    */


    @Test
    @DisplayName("Esto será un test")

    public void test() {
        System.out.println("Estoy creando usuarios un test");
    }


}