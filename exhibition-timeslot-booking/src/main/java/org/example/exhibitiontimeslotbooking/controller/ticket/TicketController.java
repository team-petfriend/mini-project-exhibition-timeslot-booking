package org.example.exhibitiontimeslotbooking.controller.ticket;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.ticket.response.TicketCodeResponse;
import org.example.exhibitiontimeslotbooking.dto.ticket.response.TicketResponse;
import org.example.exhibitiontimeslotbooking.entity.booking.Booking;
import org.example.exhibitiontimeslotbooking.service.ticket.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.Tickets.ROOT)
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping(ApiMappingPattern.Tickets.TICKETS)
    public ResponseEntity<ResponseDto<List<TicketResponse>>> getTicketByBookingId(
            @PathVariable Booking bookingId
    ) {
        ResponseDto<List<TicketResponse>> response = ticketService.getTicketByBookingId(bookingId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(ApiMappingPattern.Tickets.TICKET_USE)
    public ResponseEntity<ResponseDto<?>> useTicket(
            @PathVariable Long ticketId
    ) {
        ResponseDto<Void> response = ticketService.useTicket(ticketId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(ApiMappingPattern.Tickets.TICKET_VOID)
    public ResponseEntity<ResponseDto<?>> voidTicket(
            @PathVariable Long ticketId
    ){
        ResponseDto<Void> response = ticketService.voidTicket(ticketId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(ApiMappingPattern.Tickets.TICKET_SCAN)
    public ResponseEntity<ResponseDto<TicketResponse>> scanTicket(@RequestParam String code){
        ResponseDto<TicketResponse> response = ticketService.scanTicket(code);
        return ResponseEntity.ok().body(response);
    }

}
