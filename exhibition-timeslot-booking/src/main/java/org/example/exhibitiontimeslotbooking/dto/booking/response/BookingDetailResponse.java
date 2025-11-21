package org.example.exhibitiontimeslotbooking.dto.booking.response;

import org.example.exhibitiontimeslotbooking.common.enums.bookings.BookingStatus;
import org.example.exhibitiontimeslotbooking.entity.booking.Booking;

import java.time.LocalDateTime;

public record BookingDetailResponse(
        Long id,
        Long userId,
        Long timeslotId,
        Integer qty,
        Integer amount,
        BookingStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
    public static BookingDetailResponse from(Booking booking){
        return new BookingDetailResponse(
                booking.getId(),
                booking.getUserId().getId(),
                booking.getTimeslotId().getId(),
                booking.getQty(),
                booking.getAmount(),
                booking.getStatus(),
                booking.getCreatedAt(),
                booking.getUpdatedAt()
        );
    }
}
