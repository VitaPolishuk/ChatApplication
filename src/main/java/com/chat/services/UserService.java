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
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        List<User> userList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userList.add(user);
        }
        return userList;
    }

    public User findOneUser(int id) {
        return userRepository.findOne(id);
    }

    public User findUserByLogin(String login) {
        return userRepository.findBylogin(login);
    }

    public User findUserByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndAndPassword(login, password);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.delete(id);
    }
}

