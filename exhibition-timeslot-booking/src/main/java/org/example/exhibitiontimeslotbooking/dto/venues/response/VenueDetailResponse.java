package org.example.exhibitiontimeslotbooking.dto.venues.response;

import java.math.BigDecimal;

public record VenueDetailResponse(
        Long id,
        String name,
        String address,
        String venueMainImg,
        BigDecimal latitude,
        BigDecimal longitude
) {
}
