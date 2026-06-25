package com.udemy.SupportTicket.controller;


import com.udemy.SupportTicket.service.SupportTicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupportTicketController {

    private final SupportTicketService service;

    public SupportTicketController(SupportTicketService service) {
        this.service = service;
    }

    @GetMapping("/tickets/one-shot-with-prompt-template")
    public String oneShotWithPromptTemplate(@RequestParam String issue) {
        return service.routeOneShotWithTemplate(issue);
    }
}