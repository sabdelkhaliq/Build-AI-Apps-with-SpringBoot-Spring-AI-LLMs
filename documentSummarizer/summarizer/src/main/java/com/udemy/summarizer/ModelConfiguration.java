package com.udemy.summarizer;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfiguration {

    @Bean
    public ChatClient openaiChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel).defaultSystem("You are professional summarizer you will take document as input and summarize it to 1 sentence only capturing the main idea").build();
    }

    @Bean
    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel) {
        return ChatClient.builder(ollamaChatModel).defaultSystem("You are professional document reviewer and you will give input document review a score from 1 to 10").build();
    }
}
