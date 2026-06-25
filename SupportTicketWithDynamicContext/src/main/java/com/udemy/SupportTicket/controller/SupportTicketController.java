package com.udemy.SupportTicket.controller;


import com.udemy.SupportTicket.service.SupportTicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupportTicketController {

    private final SupportTicketService service;

    @GetMapping("/tickets/one-shot-with-prompt-template-and-dynamic-context")
    public String oneShotWithPromptTemplateAndDynamicContext(@RequestParam String issue) {
        return service.routeOneShotWithTemplateAndDynamicContext(issue);
    }
}