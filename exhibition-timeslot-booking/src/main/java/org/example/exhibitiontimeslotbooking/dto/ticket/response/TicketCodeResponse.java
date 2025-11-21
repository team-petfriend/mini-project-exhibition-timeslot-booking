package org.example.exhibitiontimeslotbooking.dto.ticket.response;

import org.example.exhibitiontimeslotbooking.common.enums.tickets.TicketStatus;
import org.example.exhibitiontimeslotbooking.entity.ticket.Ticket;

import java.time.LocalDateTime;

public record TicketCodeResponse(
        Long id,
        Long bookingId,
        String code,
        TicketStatus ticketStatus,
        LocalDateTime used
) {
    public static TicketCodeResponse from(Ticket ticket){
        return new TicketCodeResponse(
                ticket.getId(),
                ticket.getBookingId().getId(),
                ticket.getCode(),
                ticket.getTicketStatus(),
                ticket.getUsedAt()
        );
    }
}
