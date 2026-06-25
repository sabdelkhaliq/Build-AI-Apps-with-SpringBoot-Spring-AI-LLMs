package com.udemy.SupportTicket.service;

import com.udemy.SupportTicket.service.domain.ResponsibleTeamResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ResponsibleTeamResponse routeOneShotWithStructuredOutputObject(String ticket) {
        String context = this.contextBuilder.getContext();
        ResponsibleTeamResponse response = chatClient.prompt()
                .user(promptUserSpec -> promptUserSpec.text(resourceTemplate).param("context", context).param("ticket", ticket))
                .call()
                .entity(ResponsibleTeamResponse.class);
        return response;
    }

    public List<ResponsibleTeamResponse> routeOneShotWithStructuredOutputList(String ticket) {
        String context = this.contextBuilder.getContext();
        List<ResponsibleTeamResponse> response = chatClient.prompt()
                .user(promptUserSpec -> promptUserSpec.text(resourceTemplate).param("context", context).param("ticket", ticket))
                .call()
                .entity(new ParameterizedTypeReference<List<ResponsibleTeamResponse>>() {});
        return response;
    }
}
