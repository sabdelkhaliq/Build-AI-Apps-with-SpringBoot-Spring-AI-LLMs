package com.udemy.chatOllama.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OllamaController {


    private ChatClient chatClient;

    public OllamaController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }


    @GetMapping("/summarise")
    public void chat(){
        ChatOptions.Builder<?> chatOptions= ChatOptions.builder();
        String resultLowTemp, resultHighTemp;

        System.out.println("Low temperature:");
            resultLowTemp = chatClient.prompt().system("complete").user("My cat opened the fridge and found ...").options(chatOptions.temperature(0.0)).call().content();
            System.out.println(resultLowTemp);

        System.out.println("High temperature:");
            resultHighTemp = chatClient.prompt().system("complete").user("My cat opened the fridge and found ...").options(chatOptions.temperature(2.0)).call().content();
            System.out.println(resultHighTemp);

}

}
