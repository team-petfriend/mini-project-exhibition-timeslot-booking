package org.example.exhibitiontimeslotbooking.repository.ticket;

import org.example.exhibitiontimeslotbooking.entity.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
