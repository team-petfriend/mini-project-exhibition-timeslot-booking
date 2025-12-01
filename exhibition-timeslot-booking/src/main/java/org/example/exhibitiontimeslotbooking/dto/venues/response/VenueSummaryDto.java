package org.example.exhibitiontimeslotbooking.dto.venues.response;

import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.entity.venue.Venue;

public record VenueSummaryDto(
        Long id,
        String name,
        String address,
        String venueImgURL
) {
    public static VenueSummaryDto from(Venue venue) {
        if (venue == null) return null;

        return new VenueSummaryDto(
                venue.getId(),
                venue.getName(),
                venue.getAddress(),
                venue.getFileInfo().getFilePath()
        );
    }
}
