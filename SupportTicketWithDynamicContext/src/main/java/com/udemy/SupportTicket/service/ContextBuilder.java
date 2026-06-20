package com.udemy.SupportTicket.service;

import com.udemy.SupportTicket.service.domain.SupportTeam;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ContextBuilder {

    public String getContext() {
        return """
                Choose exactly one team:
                        %s
                
                        Team responsibilities:
                        %s
                
                        Important rule:
                        If the problem happens inside a billing or invoice page,
                        route it to BILLING_TEAM, even if the visible symptom is a 500 error.
                """.formatted(getTeamNames(),
                getResponisibilities());
    }

    private String getResponisibilities() {
        return Arrays.stream(SupportTeam.values()).map(team -> team.name() + ": " + team.responsibility() + ", ").collect(Collectors.joining("\n"));

    }

    private String getTeamNames() {
        return Arrays.stream(SupportTeam.values()).map(team -> team.name() + ", ").collect(Collectors.joining("\n"));
    }
}
