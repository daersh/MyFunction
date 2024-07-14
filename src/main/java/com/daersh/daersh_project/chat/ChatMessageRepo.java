package com.daersh.daersh_project.chat;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepo extends MongoRepository<Message, String> {
}
