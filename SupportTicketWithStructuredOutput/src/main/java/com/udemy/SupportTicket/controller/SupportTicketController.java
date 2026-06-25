package com.udemy.SupportTicket.controller;


import com.udemy.SupportTicket.service.SupportTicketService;
import com.udemy.SupportTicket.service.domain.ResponsibleTeamResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupportTicketController {

    private final SupportTicketService service;

    public SupportTicketController(SupportTicketService service) {
        this.service = service;
    }

    @GetMapping("/tickets/structured-output-object")
    public ResponsibleTeamResponse oneShotWithStructuredOutputObject(@RequestParam String issue) {
        return service.routeOneShotWithStructuredOutputObject(issue);
    }

    @GetMapping("/tickets/structured-output-list")
    public List<ResponsibleTeamResponse> oneShotWithStructuredOutputList(@RequestParam String issue) {
        return service.routeOneShotWithStructuredOutputList(issue);
    }
}