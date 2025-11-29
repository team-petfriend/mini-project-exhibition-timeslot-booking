package org.example.exhibitiontimeslotbooking.service.ticket.impl;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.tickets.TicketStatus;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.ticket.response.TicketCodeResponse;
import org.example.exhibitiontimeslotbooking.dto.ticket.response.TicketResponse;
import org.example.exhibitiontimeslotbooking.entity.booking.Booking;
import org.example.exhibitiontimeslotbooking.entity.ticket.Ticket;
import org.example.exhibitiontimeslotbooking.repository.ticket.TicketRepository;
import org.example.exhibitiontimeslotbooking.service.ticket.TicketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Override
    public ResponseDto<List<TicketResponse>> getTicketByBookingId(Booking bookingId) {
        List<Ticket> tickets = ticketRepository.findByBookingId(bookingId);
        List<TicketResponse> ticketList = tickets.stream()
                .map(TicketResponse::from)
                .toList();
        return ResponseDto.success(ticketList);
    }

    @Override
    public ResponseDto<Void> useTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 티켓을 찾을 수 없습니다."));
        ticket.setTicketState(TicketStatus.USED);
        ticket.setUsedAt(LocalDateTime.now());
        ticketRepository.save(ticket);
        return ResponseDto.success(null);
    }

    @Override
    public ResponseDto<Void> voidTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 티켓을 찾을 수 없습니다."));
        ticket.setTicketState(TicketStatus.VOID);
        return ResponseDto.success(null);
    }

    @Override
    public ResponseDto<TicketResponse> scanTicket(String code) {
        Ticket ticket = ticketRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("코드를 찾을 수 없습니다."));

        TicketResponse data = TicketResponse.from(ticket);
        return ResponseDto.success("success", data);
    }
}
