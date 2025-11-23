package org.example.exhibitiontimeslotbooking.dto.exhibitions.response;

import org.example.exhibitiontimeslotbooking.dto.venues.response.VenueSummaryDto;

import java.util.List;

public record ExhibitionListResponse(
        List<ExhibitionSummaryDto> exhibitionsList,
        int total
) {
}
