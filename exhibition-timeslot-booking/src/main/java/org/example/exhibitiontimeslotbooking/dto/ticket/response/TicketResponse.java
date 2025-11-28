package org.example.exhibitiontimeslotbooking.dto.ticket.response;

import org.example.exhibitiontimeslotbooking.common.enums.tickets.TicketStatus;
import org.example.exhibitiontimeslotbooking.entity.ticket.Ticket;

import java.time.LocalDateTime;

public record TicketResponse(
        Long id,
        Long bookingId,
        String code,
        TicketStatus ticketStatus,
        LocalDateTime issuedAt,
        LocalDateTime usedAt

) {
    public static TicketResponse from(Ticket ticket){
        return new TicketResponse(
                ticket.getId(),
                ticket.getBookingId().getId(),
                ticket.getCode(),
                ticket.getTicketStatus(),
                ticket.getIssuedAt(),
                ticket.getUsedAt()
        );
    }
}
