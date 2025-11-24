package org.example.exhibitiontimeslotbooking.dto.exhibitions.response;

import java.util.List;

public record ExhibitionListResponseDto(
        List<ExhibitionSummaryDto> exhibitionsList,
        int total
) {
}
