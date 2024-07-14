package com.daersh.daersh_project.chat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@Document(collection = "messages")
@ToString
public class Message {
    @Id
    private String id;
    private String sender;
    private String content;
    private Instant timestamp;
}
