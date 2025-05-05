package com.inrohack.security.services;


import com.inrohack.security.models.User;
import com.inrohack.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
//.services.UserService
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //creo el méthodo para que encripte el password antes
    //de guardarse en la BBDD
    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> getByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    // Compara el password descifrando el almacenado en la BBDD
    public boolean checkPasword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }


}
