package org.example.exhibitiontimeslotbooking.dto.venues.response;

public record VenueSummaryDto(
        Long id,
        String name,
        String address,
        String venueMainImg
) {

}
