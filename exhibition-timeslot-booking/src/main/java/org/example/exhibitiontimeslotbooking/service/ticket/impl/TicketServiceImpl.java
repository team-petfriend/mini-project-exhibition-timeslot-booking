package org.example.exhibitiontimeslotbooking.service.ticket.impl;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.ticket.response.TicketCodeResponse;
import org.example.exhibitiontimeslotbooking.dto.ticket.response.TicketResponse;
import org.example.exhibitiontimeslotbooking.repository.ticket.TicketRepository;
import org.example.exhibitiontimeslotbooking.service.ticket.TicketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Override
    public ResponseDto<List<TicketResponse>> getTicketByBookingId(Long bookingId) {
        return null;
    }

    @Override
    public ResponseDto<Void> useTicket(Long ticketId) {
        return null;
    }

    @Override
    public ResponseDto<Void> voidTicket(Long ticketId) {
        return null;
    }

    @Override
    public ResponseDto<TicketCodeResponse> scanTicket(String code) {
        return null;
    }
}
