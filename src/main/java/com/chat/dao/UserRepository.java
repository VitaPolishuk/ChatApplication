package com.chat.dao;

import com.chat.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);
}


