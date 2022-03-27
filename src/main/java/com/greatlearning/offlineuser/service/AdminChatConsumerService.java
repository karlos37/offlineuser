package com.greatlearning.offlineuser.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AdminChatConsumerService {

    @KafkaListener(topics = "admin-user-chats", groupId = "group-id")
    public void getMessage(String message){
        System.out.println("Admin messaged : " +message);
    }
}
