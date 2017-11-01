package com.chat.controllers;

import com.chat.entities.User;
import com.chat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {
    private final UserService userService;

    @Autowired
    public WebController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public User registration(@RequestBody User userData) {
        return userService.saveUser(userData);
    }

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    @ResponseBody
    public User authentication(@RequestBody User userData) {
        return userService.findUserByLoginAndPassword(userData.getLogin(), userData.getPassword());
    }
}
