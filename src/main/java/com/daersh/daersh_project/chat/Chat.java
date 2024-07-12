package com.daersh.daersh_project.chat;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "chatMessages")
public class Chat {
    private String sender;
    private String content;
    private String timestamp;
}
