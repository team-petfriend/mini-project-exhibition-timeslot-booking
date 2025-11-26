package org.example.exhibitiontimeslotbooking.dto.booking.response;

import org.example.exhibitiontimeslotbooking.common.enums.bookings.BookingStatus;
import org.example.exhibitiontimeslotbooking.entity.booking.Booking;

import java.time.LocalDateTime;

public record BookingResponse(
        Long id,
        Long userId,
        Long timeslotId,
        Integer amount,
        BookingStatus status,
        LocalDateTime createdAt
) {
    public static BookingResponse from(Booking booking){
        return new BookingResponse(
                booking.getId(),
                booking.getUser().getId(),
                booking.getTimeslot().getId(),
                booking.getAmount(),
                booking.getStatus(),
                booking.getCreatedAt()
        );
    }
}
