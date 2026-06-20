package com.udemy.SupportTicket;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    ChatClient supportTicketChatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("""
                        You are an internal support ticket routing assistant.

                        Your job is to route support tickets to exactly one team.

                        Allowed teams:
                        - BILLING_TEAM
                        - TECHNICAL_TEAM

                        Keep the answer short.
                        Do not write a customer-facing email.
                        Do not solve the ticket.
                        Only decide the correct team.
                        """)
                .build();
    }
}

