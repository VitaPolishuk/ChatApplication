package com.chat.services;

import com.chat.dao.ChatRepository;
import com.chat.entities.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ChatService {
    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public void save(Chat chat) {
        chatRepository.save(chat);
    }

    public List<Chat> findAll() {
        List<Chat> userList = new ArrayList<>();
        for (Chat user : chatRepository.findAll()) {
            userList.add(user);
        }
        return userList;
    }

}