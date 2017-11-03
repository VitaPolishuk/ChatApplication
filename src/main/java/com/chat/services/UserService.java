package com.chat.services;


import com.chat.dao.UserRepository;
import com.chat.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User saveUser(User user) {
        User instance = userRepository.findByLogin(user.getLogin());
        if (instance != null) {
            return instance;
        }
        return userRepository.save(user);
    }

}

