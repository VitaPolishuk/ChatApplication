package com.chat.controllers;

import com.chat.entities.User;
import com.chat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    private UserService userService;

    @Autowired
    public WebController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String example() {
        return "index";
    }

    @RequestMapping("/chat")
    public String chat(){
        return "html/chat";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public int registration(@RequestBody User userData) {
        User user = new User();
        if (userService.findUserByLogin(userData.getLogin()) == null) {
            user.setLogin(userData.getLogin());
            user.setPassword(userData.getPassword());
            userService.saveUser(user);
            return 1;
        }
        else{
            return 0;
        }
    }
    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    @ResponseBody
    public String authentication(@RequestBody User userData) {
        if(userService.findUserByLoginAndPassword(userData.getLogin(), userData.getPassword()) == null){
            return "";
        }
        else{
            return userData.getLogin();
        }
    }

//    @MessageMapping("/chatMessage")
//    @SendTo("/messages")
//    public Greeting chatMessage(HelloMessage message) throws Exception {
//        Thread.sleep(1000); // simulated delay
//        return new Greeting("Hello, " + message.getName() + "!");
//    }

}