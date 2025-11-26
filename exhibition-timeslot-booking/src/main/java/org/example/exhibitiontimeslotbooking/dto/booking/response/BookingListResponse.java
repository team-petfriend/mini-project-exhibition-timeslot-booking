package org.example.exhibitiontimeslotbooking.dto.booking.response;

import java.util.List;

public record BookingListResponse(
        List<BookingResponse> bookings
        ) {
    public static BookingListResponse from(List<BookingResponse> bookings) {
        return new BookingListResponse(bookings);
    }

}
