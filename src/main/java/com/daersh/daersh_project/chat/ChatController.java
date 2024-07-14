package com.daersh.daersh_project.chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatMessageRepo chatMessageRepo;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/messages")
    public Message sendMessage(@Payload Message chatMessage) {
        System.out.println("chatMessage = " + chatMessage);

        chatMessage.setTimestamp(Instant.now());
        return chatMessageRepo.save(chatMessage);
    }
    @MessageMapping("/chat.getMessages")
    @SendTo("/topic/messages")
    public List<Message> getMessages() {
        List<Message> messages = chatMessageRepo.findAll();
        System.out.println("Fetched messages: " + messages);
        return messages;
    }
    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return chatMessageRepo.findAll();
    }
}