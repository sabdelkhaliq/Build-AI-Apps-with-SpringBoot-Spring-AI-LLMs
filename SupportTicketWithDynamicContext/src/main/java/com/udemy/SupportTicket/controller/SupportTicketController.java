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

    @GetMapping("/tickets/bad")
    public String badPrompt(@RequestParam String issue) {
        return service.routeBadPrompt(issue);
    }

    @GetMapping("/tickets/zero-shot")
    public String zeroShot(@RequestParam String issue) {
        return service.routeZeroShot(issue);
    }

    @GetMapping("/tickets/one-shot")
    public String oneShot(@RequestParam String issue) {
        return service.routeOneShot(issue);
    }

    @GetMapping("/tickets/one-shot-with-prompt-template")
    public String oneShotWithPromptTemplate(@RequestParam String issue) {
        return service.routeOneShotWithTemplate(issue);
    }

    @GetMapping("/tickets/one-shot-with-prompt-template-and-dynamic-context")
    public String oneShotWithPromptTemplateAndDynamicContext(@RequestParam String issue) {
        return service.routeOneShotWithTemplateAndDynamicContext(issue);
    }
}