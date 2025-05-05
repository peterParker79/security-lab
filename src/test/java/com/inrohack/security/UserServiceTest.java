package com.inrohack.security;

import com.inrohack.security.models.User;
import com.inrohack.security.repositories.UserRepository;
import com.inrohack.security.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {


    @Autowired
     UserService userService; //userService ya usa User repository

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    User user;
/*
    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("usertest");
        user.setPassword("1234");
        System.out.println("El usuario inicial es: " + user);

        userService.saveUser(user); //el saveUser encripta el password antes de almacenarlo en BBDD
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
        System.out.println("Creating users with pass encrypted");
    }

    @Test
    @DisplayName("Password encription is ok")
    public void passwordIsCorrectTest() {
        Optional<User> userOptional = userService.getByUsername("usertest");
        User user= userOptional.get();
        assertTrue (user.getPassword().startsWith("$2a$"));
        System.out.println("Password is ok");
    }


}