package com.inrohack.security.services;


import com.inrohack.security.models.User;
import com.inrohack.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getByUsername(String username){
        return userRepository.findUserByUsername(username);

    }
}
