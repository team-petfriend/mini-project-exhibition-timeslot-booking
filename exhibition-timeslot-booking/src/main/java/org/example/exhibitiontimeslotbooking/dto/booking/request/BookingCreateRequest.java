package org.example.exhibitiontimeslotbooking.dto.booking.request;


public record BookingCreateRequest(
        Long timeslotId,
        int qty,
        int amount
) {
}
