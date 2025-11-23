package org.example.exhibitiontimeslotbooking.dto.venues.response;

import org.example.exhibitiontimeslotbooking.entity.venue.Venue;

import java.math.BigDecimal;

public record VenueDetailResponse(
        Long id,
        String name,
        String address,
        VenueFileResponse venueMainImg,
        BigDecimal latitude,
        BigDecimal longitude
) {
    public static VenueDetailResponse from(Venue venue) {

        if (venue == null) return null;

        return new VenueDetailResponse(
                venue.getId(),
                venue.getName(),
                venue.getAddress(),
                VenueFileResponse.from(venue.getFileInfo()),
                venue.getLatitude(),
                venue.getLongitude()
        );
    }
}
