package org.example.exhibitiontimeslotbooking.service.ticket.impl;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.repository.ticket.TicketRepository;
import org.example.exhibitiontimeslotbooking.service.ticket.TicketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
}
