package org.example.exhibitiontimeslotbooking.entity.payment;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.payment.PaymentMethod;
import org.example.exhibitiontimeslotbooking.common.enums.payment.PaymentStatus;
import org.example.exhibitiontimeslotbooking.common.enums.tickets.TicketStatus;
import org.example.exhibitiontimeslotbooking.entity.base.BaseTimeEntity;
import org.example.exhibitiontimeslotbooking.entity.booking.Booking;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets",
        indexes = {@Index(name = "idx_payment_booking", columnList = "booking_id, status")}
)
@Getter
@NoArgsConstructor
public class Payment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "booking_id", nullable = false, foreignKey = @ForeignKey(name = "fk_payment_booking_id"))
    private Booking bookingId;

    @Column(name = "amount", nullable = false)
    @Check(constraints = "amount > 0")
    private int amount;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency = "KRW";

    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false, length = 20)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PAID;

    @CreationTimestamp
    @Column(name = "paid_at", updatable = false)
    private LocalDateTime paidAt;


    public void markPending() {
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public void markFailed() {
        this.paymentStatus = PaymentStatus.FAILED;
    }

    public void markRefunded() {
        this.paymentStatus = PaymentStatus.REFUNDED;
    }

}
