package com.chat.services;


import com.chat.dao.UserRepository;
import com.chat.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    public User saveUser(User user) {
        User exist = userRepository.findByLogin(user.getLogin());
        if (exist != null) {
            return exist;
        }

        return userRepository.save(user);
    }
}

