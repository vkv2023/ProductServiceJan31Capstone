package com.example.ProductServiceJan31Capstone.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.ai.chat.client.ChatClient;

@RestController
@RequestMapping("/products")
public class ChatController {

    ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message){
            return chatClient.prompt().user(message).call().content();
    }

    //{"response" :"some message"};
}
