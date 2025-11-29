package org.example.exhibitiontimeslotbooking.repository.ticket;

import org.example.exhibitiontimeslotbooking.entity.booking.Booking;
import org.example.exhibitiontimeslotbooking.entity.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByBookingId(Booking bookingId);

    Optional<Ticket> findByCode(String code);
}
