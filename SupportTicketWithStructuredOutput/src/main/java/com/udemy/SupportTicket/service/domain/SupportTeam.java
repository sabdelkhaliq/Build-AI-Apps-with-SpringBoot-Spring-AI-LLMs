package com.udemy.SupportTicket.service.domain;

public enum SupportTeam {


    BILLING_TEAM(" Handles billing pages, invoice pages, payments, refunds, subscriptions, and plan upgrades."),
    TECHNICAL_TEAM("Handles infrastructure, server availability, login failures, API failures, database issues, network problems, and deployment issues."),;

    private final String responsibility;

    SupportTeam(String responsibility) {
        this.responsibility = responsibility;
    }

    public String responsibility() {
        return responsibility;
    }
}
