package org.example.exhibitiontimeslotbooking.controller.ticket;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.example.exhibitiontimeslotbooking.service.ticket.TicketService;
import org.example.exhibitiontimeslotbooking.service.ticket.impl.TicketServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.Tickets.ROOT)
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
}
