package org.example.exhibitiontimeslotbooking.dto.venues.response;
import java.util.List;

public record VenueListResponseDto(
        List<VenueSummaryDto> venuesList,
        int total
){
}
