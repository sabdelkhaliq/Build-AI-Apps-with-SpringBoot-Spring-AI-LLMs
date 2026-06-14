package com.udemy.summarizer;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class DocumentSummarizer {

    private ChatClient openaiChatClient;
    private ChatClient ollamaChatClient;

    public DocumentSummarizer(@Qualifier("openaiChatClient") ChatClient openaiChatClient,@Qualifier("ollamaChatClient") ChatClient ollamaChatClient) {
        this.openaiChatClient = openaiChatClient;
        this.ollamaChatClient = ollamaChatClient;
    }

    @GetMapping("/summarize")
    public void summarize(){

        // 1. read document
        String content = readDocument("story.txt");
        System.out.println(content);
        System.out.println("-------");

        // 2. summarise document
        String summary = this.openaiChatClient.prompt("summarise document:"+content).call().content();

        System.out.println("openai summary"+summary);
        System.out.println("-------");

        // 3. review document
        String review = this.ollamaChatClient.prompt("review summarization of document:"+content+"final summary:"+summary).call().content();

        System.out.println("ollama review"+review);
    }


        private String readDocument(String documentName) {
            ClassPathResource document = new ClassPathResource(documentName);

            if (!document.exists()) {
                throw new IllegalArgumentException("Document not found: " + documentName);
            }

            try {
                return StreamUtils.copyToString(document.getInputStream(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new IllegalStateException("Failed to read document: " + documentName, e);
            }
        }
}
