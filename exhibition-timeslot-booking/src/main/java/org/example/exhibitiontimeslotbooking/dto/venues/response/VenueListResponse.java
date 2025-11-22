package org.example.exhibitiontimeslotbooking.dto.venues.response;


import java.util.List;

public record VenueListResponse(
        List<VenueSummaryDto> venuesList,
        int total
){
}
