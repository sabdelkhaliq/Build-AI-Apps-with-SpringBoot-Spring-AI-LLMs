package com.udemy.SupportTicket.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class SupportTicketService {
    private final ChatClient chatClient;

    public SupportTicketService(ChatClient supportTicketChatClient) {
        this.chatClient = supportTicketChatClient;
    }

    /*
     * Team responsibilities:
     *
     * TECHNICAL_TEAM:
     * - Infrastructure problems
     * - Server availability
     * - Login system failures
     * - API failures
     * - Database issues
     * - Network and deployment problems
     *
     * BILLING_TEAM:
     * - Billing pages
     * - Invoice pages
     * - Payment issues
     * - Refunds
     * - Subscriptions
     * - Plan upgrades
     *
     * Important business rule:
     * If the problem happens inside a billing or invoice page,
     * route it to BILLING_TEAM, even if the visible symptom is a 500 error.
     */

    public String routeBadPrompt(String ticket) {
        return chatClient.prompt()
                .user("""
                        Handle this support ticket:

                        %s
                        """.formatted(ticket))
                .call()
                .content();
    }

    public String routeZeroShot(String ticket) {
        return chatClient.prompt()
                .user("""
                        Route this support ticket to the correct team.

                        Choose exactly one team:
                        - BILLING_TEAM
                        - TECHNICAL_TEAM

                        Return exactly:
                        Team: <BILLING_TEAM or TECHNICAL_TEAM>
                        Reason: <one short sentence>

                        Ticket:
                        %s
                        """.formatted(ticket))
                .call()
                .content();
    }

    public String routeOneShot(String ticket) {
        return chatClient.prompt()
                .user("""
                        Route this support ticket to the correct team.

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

                        Example:

                        Ticket:
                        When I open the invoice page, I get a 500 error and cannot download my invoice.

                        Answer:
                        Team: BILLING_TEAM
                        Reason: The issue happens inside the invoice page, so it belongs to the billing team even though it shows a 500 error.

                        Now route this ticket:

                        Ticket:
                        %s

                        Return exactly:
                        Team: <BILLING_TEAM or TECHNICAL_TEAM>
                        Reason: <one short sentence>
                        """.formatted(ticket))
                .call()
                .content();
    }
}
