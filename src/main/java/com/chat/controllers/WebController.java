package com.chat.controllers;

import com.chat.entities.Chat;
import com.chat.entities.User;
import com.chat.services.ChatService;
import com.chat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WebController {
    private final UserService userService;
    private final ChatService chatService;

    @Autowired
    public WebController(UserService userService, ChatService chatService) {
        this.userService = userService;
        this.chatService = chatService;
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

    @MessageMapping("/chatMessage")
    @SendTo("/messages")
    public Chat chatMessage(Chat chatData) throws Exception {
        return chatService.saveChat(chatData);
    }

}