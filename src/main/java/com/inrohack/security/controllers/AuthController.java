package com.inrohack.security.controllers;

import com.inrohack.security.models.ERole;
import com.inrohack.security.models.User;
import com.inrohack.security.services.JwtService;
import com.inrohack.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody User user){
        Optional<User> optionalUser = userService.getByUsername(user.getUsername());
            if (optionalUser.isPresent()){
                User foundUser = optionalUser.get();

                if (userService.checkPasword(foundUser, user.getPassword())){
                    List<ERole> rolesNames= foundUser.getRoles().stream()
                            .map(role -> role.getName())
                            .collect(Collectors.toList());

                    String token = jwtService.generateToken(foundUser.getUsername(), rolesNames.toString());
                return ResponseEntity.ok(token);
                }else{
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed.");
                }
            }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
    }

}
