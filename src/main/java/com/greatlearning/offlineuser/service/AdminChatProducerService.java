package com.greatlearning.offlineuser.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AdminChatProducerService {

    private static final String TOPIC = "user-admin-chats";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public AdminChatProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String sendMessage(String message){
        this.kafkaTemplate.send(TOPIC, message);
        return message;
    }
}
