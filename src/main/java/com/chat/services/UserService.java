package com.chat.services;


import com.chat.dao.UserRepository;
import com.chat.entities.User;
import com.chat.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private JwtGenerator jwtGenerator;


    @Autowired
    public UserService(UserRepository userRepository, JwtGenerator jwtGenerator) {
        this.userRepository = userRepository;
        this.jwtGenerator = jwtGenerator;
    }

    public String saveUser(User user) {

        User instance = userRepository.findByLogin(user.getLogin());
        if (instance != null) {
            return "";
        }
        else{
            String token = jwtGenerator.generate(user);
            user.setToken(token);
            userRepository.save(user);
            return token;
        }
    }

}

