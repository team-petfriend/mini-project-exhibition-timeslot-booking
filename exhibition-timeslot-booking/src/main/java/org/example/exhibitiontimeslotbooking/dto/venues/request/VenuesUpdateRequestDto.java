package org.example.exhibitiontimeslotbooking.dto.venues.request;

import java.math.BigDecimal;

public record VenuesUpdateRequestDto(
        String name,

        String address,

        BigDecimal latitude,

        BigDecimal longitude
        
) {
}
