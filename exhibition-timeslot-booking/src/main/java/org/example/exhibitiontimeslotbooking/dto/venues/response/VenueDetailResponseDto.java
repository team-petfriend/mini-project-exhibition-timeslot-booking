package org.example.exhibitiontimeslotbooking.dto.venues.response;

import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.entity.venue.Venue;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VenueDetailResponseDto(
        Long id,
        String name,
        String address,
        String venueImgURL ,
        BigDecimal latitude,
        BigDecimal longitude,
        LocalDateTime created_at,
        LocalDateTime updated_at

) {
    public static VenueDetailResponseDto from(Venue venue) {

        if (venue == null) return null;

        FileInfo fileInfo = venue.getFileInfo();
        String venueImgURL = null;
        if (fileInfo != null) {
            venueImgURL = "/upload/" + fileInfo.getFilePath().replace("\\", "/");
        }

        return new VenueDetailResponseDto(
                venue.getId(),
                venue.getName(),
                venue.getAddress(),
                venueImgURL,
                venue.getLatitude(),
                venue.getLongitude(),
                venue.getCreatedAt(),
                venue.getUpdatedAt()
        );
    }
}
