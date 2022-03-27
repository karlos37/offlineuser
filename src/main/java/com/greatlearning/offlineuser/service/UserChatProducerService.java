package com.greatlearning.offlineuser.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserChatProducerService {

    private static final String TOPIC = "user-chats";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public UserChatProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String sendMessage(String message){
        this.kafkaTemplate.send(TOPIC, message);
        return message;
    }
}
