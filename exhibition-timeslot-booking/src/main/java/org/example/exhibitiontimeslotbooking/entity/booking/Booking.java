package org.example.exhibitiontimeslotbooking.entity.booking;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.bookings.BookingStatus;
import org.example.exhibitiontimeslotbooking.entity.payment.Payment;
import org.example.exhibitiontimeslotbooking.entity.ticket.Ticket;
import org.example.exhibitiontimeslotbooking.entity.base.BaseTimeEntity;
import org.example.exhibitiontimeslotbooking.entity.timeslot.Timeslot;
import org.example.exhibitiontimeslotbooking.entity.user.User;
import org.hibernate.annotations.Check;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bookings",
        indexes = {
                @Index(name = "idx_booking_user", columnList = "user_id, status"),
                @Index(name = "idx_booking_timeslot", columnList = "timeslot_id")
        })
@Getter
@NoArgsConstructor
public class Booking extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_booking_user_id"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "timeslot_id", nullable = false, foreignKey = @ForeignKey(name = "fk_booking_timeslot_id"))
    private Timeslot timeslot;

    @Column(name = "qty", nullable = false)
    @Check(constraints = "qty > 0")
    private int qty;

    @Column(name = "amount", nullable = false)
    @Check(constraints = "amount > 0")
    private int amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private BookingStatus status = BookingStatus.PENDING ;


    @OneToMany(mappedBy = "bookingId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> tickets = new HashSet<>();

    @OneToMany(mappedBy = "bookingId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Payment> payments = new HashSet<>();




    @Builder
    public Booking(@NotNull User user, Timeslot timeslot, int qty, int amount, BookingStatus status ){
        this.user = user;
        this.timeslot = timeslot;
        this.qty = qty;
        this.amount = amount;
        this.status = (status != null) ? status : BookingStatus.PENDING;
    }

    public void setBookingStatus(BookingStatus status){
        this.status = status;
    }


}

