package com.greatlearning.offlineuser.controller;

import com.greatlearning.offlineuser.service.AdminChatProducerService;
import com.greatlearning.offlineuser.service.UserChatProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final AdminChatProducerService adminChatProducerService;

    private final UserChatProducerService userChatProducerService;

    public ChatController(AdminChatProducerService adminChatProducerService, UserChatProducerService userChatProducerService) {
        this.adminChatProducerService = adminChatProducerService;
        this.userChatProducerService = userChatProducerService;
    }


    @PostMapping("chats/admin")
    public void sendAdminMessage(@RequestParam String message){
        this.adminChatProducerService.sendMessage(message);
    }

    @PostMapping("chats/user")
    public void sendUserMessage(@RequestParam String message){
        this.userChatProducerService.sendMessage(message);
    }
}
