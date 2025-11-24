package org.example.exhibitiontimeslotbooking.dto.venues.response;

import org.example.exhibitiontimeslotbooking.entity.venue.Venue;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VenueDetailResponseDto(
        Long id,
        String name,
        String address,
        VenueFileResponseDto venueMainImg,
        BigDecimal latitude,
        BigDecimal longitude,
        LocalDateTime created_at

) {
    public static VenueDetailResponseDto from(Venue venue) {

        if (venue == null) return null;

        return new VenueDetailResponseDto(
                venue.getId(),
                venue.getName(),
                venue.getAddress(),
                VenueFileResponseDto.from(venue.getFileInfo()),
                venue.getLatitude(),
                venue.getLongitude(),
                venue.getCreatedAt()
        );
    }
}
