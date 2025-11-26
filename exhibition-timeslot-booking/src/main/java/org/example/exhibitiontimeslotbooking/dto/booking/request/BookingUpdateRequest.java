package org.example.exhibitiontimeslotbooking.dto.booking.request;

import org.example.exhibitiontimeslotbooking.common.enums.bookings.BookingStatus;

public record BookingUpdateRequest(
        BookingStatus status
) {
}
