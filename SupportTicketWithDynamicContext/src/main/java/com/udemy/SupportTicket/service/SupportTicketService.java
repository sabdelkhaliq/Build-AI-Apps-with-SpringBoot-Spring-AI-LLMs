package com.udemy.SupportTicket.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class SupportTicketService {
    private final ChatClient chatClient;
    private Resource resourceTemplate;
    private ContextBuilder contextBuilder;

    public SupportTicketService(ChatClient supportTicketChatClient, @Value("classpath:support-ticket.st") Resource resourceTemplate,  ContextBuilder contextBuilder) {
        this.chatClient = supportTicketChatClient;
        this.resourceTemplate = resourceTemplate;
        this.contextBuilder = contextBuilder;
    }

    public String routeOneShotWithTemplateAndDynamicContext(String ticket) {
        String context = this.contextBuilder.getContext();
        return chatClient.prompt()
                .user(promptUserSpec -> promptUserSpec.text(resourceTemplate).param("context", context).param("ticket",  ticket))
                .call()
                .content();
    }
}
