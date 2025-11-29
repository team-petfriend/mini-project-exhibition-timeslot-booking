package org.example.exhibitiontimeslotbooking.service.ticket;

import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.ticket.response.TicketCodeResponse;
import org.example.exhibitiontimeslotbooking.dto.ticket.response.TicketResponse;
import org.example.exhibitiontimeslotbooking.entity.booking.Booking;

import java.util.List;

public interface TicketService {
    ResponseDto<List<TicketResponse>> getTicketByBookingId(Booking bookingId);

    ResponseDto<Void> useTicket(Long ticketId);

    ResponseDto<Void> voidTicket(Long ticketId);

    ResponseDto<TicketResponse> scanTicket(String code);
}
