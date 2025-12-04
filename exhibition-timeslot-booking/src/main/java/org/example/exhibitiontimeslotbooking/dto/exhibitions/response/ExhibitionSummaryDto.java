package org.example.exhibitiontimeslotbooking.dto.exhibitions.response;

import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.CapacityPolicy;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.ExhibitionStatus;
import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;
import org.example.exhibitiontimeslotbooking.entity.file.ExhibitionFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public record ExhibitionSummaryDto(
        Long id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        ExhibitionStatus status,
        CapacityPolicy capacityPolicy,
        List<String> exhibitionImgURL,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ExhibitionSummaryDto from( Exhibition exhibition ) {

        if (exhibition == null) return null;

        List<String> exhibitionImgURL = exhibition.getExhibitionFiles().stream()
                .map(ExhibitionFile::getFileInfo)
                .filter(Objects::nonNull)
                .map(fileInfo -> "/upload/" + fileInfo.getFilePath().replace("\\", "/"))
                .toList();

        return new ExhibitionSummaryDto(
                exhibition.getId(),
                exhibition.getTitle(),
                exhibition.getDescription(),
                exhibition.getStartDate(),
                exhibition.getEndDate(),
                exhibition.getExhibitionStatus(),
                exhibition.getCapacityPolicy(),
                exhibitionImgURL,
                exhibition.getCreatedAt(),
                exhibition.getUpdatedAt()
        );
    }
}
