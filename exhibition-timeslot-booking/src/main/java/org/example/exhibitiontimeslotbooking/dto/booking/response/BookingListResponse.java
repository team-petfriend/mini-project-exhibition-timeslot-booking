package org.example.exhibitiontimeslotbooking.dto.booking.response;

import org.example.exhibitiontimeslotbooking.common.enums.bookings.BookingStatus;
import org.example.exhibitiontimeslotbooking.entity.booking.Booking;

import java.time.LocalDateTime;

public record BookingListResponse(
        Long id,
        Long userId,
        Long timeslotId,
        Integer amount,
        BookingStatus status,
        LocalDateTime createdAt
) {
    public static BookingListResponse from(Booking booking){
        return new BookingListResponse(
                booking.getId(),
                booking.getUserId().getId(),
                booking.getTimeslotId().getId(),
                booking.getAmount(),
                booking.getStatus(),
                booking.getCreatedAt()
        );
    }
}
