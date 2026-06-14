package com.udemy.chat.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAiController {

    private ChatClient chatClient;

    public OpenAiController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping("/chat")
    public String chat(@RequestParam String message) {
        ChatClient.ChatClientRequestSpec user = this.chatClient.prompt().user(message);
        ChatClient.CallResponseSpec call = user.call();
        return call.content();
    }
}
