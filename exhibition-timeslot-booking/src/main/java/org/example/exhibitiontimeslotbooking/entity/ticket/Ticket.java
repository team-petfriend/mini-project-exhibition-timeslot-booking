package org.example.exhibitiontimeslotbooking.entity.ticket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.tickets.TicketStatus;
import org.example.exhibitiontimeslotbooking.entity.booking.Booking;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets",
        indexes = {@Index(name = "idx_ticket_booking", columnList = "booking_id, status")},
        uniqueConstraints = @UniqueConstraint(name = "uk_ticket_code", columnNames = "code")
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = false, foreignKey = @ForeignKey(name = "fk_ticket_booking_id"))
    private Booking bookingId;

    @Column(name = "code", nullable = false, length = 40)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TicketStatus ticketStatus = TicketStatus.ISSUED;

    @CreationTimestamp
    private LocalDateTime issuedAt;

    @CreationTimestamp
    private LocalDateTime usedAt;

    public static Ticket of(Booking bookingId, String code, TicketStatus ticketStatus){
        return Ticket.builder()
                .bookingId(bookingId)
                .code(code)
                .ticketStatus(ticketStatus)
                .build();
    }


}
