package org.example.exhibitiontimeslotbooking.service.impl.ticket;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.repository.ticket.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class TicketServiceImpl {
    private final TicketRepository ticketRepository;
}
