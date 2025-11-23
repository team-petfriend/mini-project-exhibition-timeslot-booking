package org.example.exhibitiontimeslotbooking.dto.exhibitions.response;

import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.CapacityPolicy;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.ExhibitionStatus;
import org.example.exhibitiontimeslotbooking.dto.exbitions_file.response.ExhibitionFileDto;
import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ExhibitionSummaryDto(
        Long id,
        String title,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        ExhibitionStatus status,
        CapacityPolicy capacityPolicy,
        List<ExhibitionFileDto> exhibitionsFiles,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ExhibitionSummaryDto from( Exhibition exhibition ) {

        if (exhibition == null) return null;

        return new ExhibitionSummaryDto(
                exhibition.getId(),
                exhibition.getTitle(),
                exhibition.getDescription(),
                exhibition.getStartDate(),
                exhibition.getEndDate(),
                exhibition.getExhibitionStatus(),
                exhibition.getCapacityPolicy(),
                exhibition.getExhibitionFiles().stream().map(fileInfo -> ExhibitionFileDto.from(fileInfo.getFileInfo())).collect(Collectors.toList()),
                exhibition.getCreatedAt(),
                exhibition.getUpdatedAt()
        );
    }
}
