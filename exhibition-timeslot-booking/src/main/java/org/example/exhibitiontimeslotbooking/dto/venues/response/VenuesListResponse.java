package org.example.exhibitiontimeslotbooking.dto.venues.response;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.entity.venue.Venue;

public record VenuesListResponse(
        Long id,
        String name,
        String address,
        String imageUrl
) {
    public static VenuesListResponse from(Venue venue) {
        if (venue == null) return null;

            String imageUrl = null;
            if (venue.getFileInfo() != null) {
                imageUrl = "/venus/"
            }


        return new VenuesListResponse(
            venue.getId(),
            venue.getName(),
            venue.getAddress(),
                imageUrl
        );
    }
}
