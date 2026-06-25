package com.udemy.SupportTicket.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class SupportTicketService {
    private final ChatClient chatClient;
    private Resource resourceTemplate;

    public SupportTicketService(ChatClient supportTicketChatClient, @Value("classpath:support-ticket.st") Resource resourceTemplate) {
        this.chatClient = supportTicketChatClient;
        this.resourceTemplate = resourceTemplate;
    }

    public String routeOneShotWithTemplate(String ticket) {
        String context = """
                Choose exactly one team:
                - BILLING_TEAM
                - TECHNICAL_TEAM

                Team responsibilities:

                TECHNICAL_TEAM:
                Handles infrastructure, server availability, login failures,
                API failures, database issues, network problems, and deployment issues.

                BILLING_TEAM:
                Handles billing pages, invoice pages, payments, refunds,
                subscriptions, and plan upgrades.

                Important rule:
                If the problem happens inside a billing or invoice page,
                route it to BILLING_TEAM, even if the visible symptom is a 500 error.
                """;

        return chatClient.prompt()
                .user(promptUserSpec -> promptUserSpec.text(resourceTemplate).param("context", context).param("ticket", ticket))
                .call()
                .content();
    }

}
