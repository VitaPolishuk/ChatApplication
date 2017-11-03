package com.chat.services;

import com.chat.dao.ChatRepository;
import com.chat.dao.UserRepository;
import com.chat.entities.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public Chat saveChat(Chat chatData) {
        Chat chat = new Chat();
        chat.setUser(userRepository.findByLogin(chatData.getLogin()));
        chat.setMessage(chatData.getMessage());
        chat.setDate(getFormatDate());
        chat.setLogin(chatData.getLogin());
        return chatRepository.save(chat);
    }

    public String getFormatDate() {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("F MMMM y' at ' HH:mm:ss");
        return formatForDateNow.format(dateNow);
    }

}