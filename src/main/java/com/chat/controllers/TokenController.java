package com.chat.controllers;

import com.chat.entities.User;
import com.chat.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TokenController {
    private final UserService userService;

    public TokenController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/token")
    public ResponseEntity<?> registration(@RequestBody User userData) {
        return ResponseEntity.ok(userService.saveUser(userData));
    }
}



