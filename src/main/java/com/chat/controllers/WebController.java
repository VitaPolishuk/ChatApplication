package com.chat.controllers;

import com.chat.entities.Chat;
import com.chat.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {
    private final ChatService chatService;

    @Autowired
    public WebController(ChatService chatService) {
        this.chatService = chatService;
    }

    @RequestMapping(value = "/authentication")
    @ResponseBody
    public ResponseEntity<?> authentication() {
        return ResponseEntity.ok("ok");
    }

    @MessageMapping("/chatMessage")
    @SendTo("/messages")
    public Chat chatMessage(Chat chatData) throws Exception {
        return chatService.saveChat(chatData);
    }

}