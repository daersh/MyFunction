package com.daersh.daersh_project.chat;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "chatMessages")
public class Chat {
    private String content;
    private String sender;
    private MessageType type;

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    // Getters and setters
}
