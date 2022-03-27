package com.greatlearning.offlineuser.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserChatConsumerService {

    @KafkaListener(topics = "user-chats", groupId = "group-id")
    public void getMessage(String message){
        System.out.println("User messaged : " +message);
    }
}
