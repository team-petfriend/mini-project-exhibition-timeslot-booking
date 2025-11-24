package org.example.exhibitiontimeslotbooking.dto.exhibitions.response;

import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.CapacityPolicy;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.ExhibitionStatus;
import org.example.exhibitiontimeslotbooking.dto.exbitions_file.response.ExhibitionFileResponseDto;
import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;
import org.example.exhibitiontimeslotbooking.entity.timeslot.Timeslot;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;

import java.util.stream.Collectors;

public record ExhibitionDetailResponseDto(
        Long id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        ExhibitionStatus exhibitionStatus,
        CapacityPolicy capacityPolicy,
        List<Timeslot> timeslots,
        List<ExhibitionFileResponseDto> exhibitionsFiles,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ExhibitionDetailResponseDto from(Exhibition exhibition ) {

        if (exhibition == null) return null;

        return new ExhibitionDetailResponseDto(
                exhibition.getId(),
                exhibition.getTitle(),
                exhibition.getDescription(),
                exhibition.getStartDate(),
                exhibition.getEndDate(),
                exhibition.getExhibitionStatus(),
                exhibition.getCapacityPolicy(),
                exhibition.getTimeslots().stream().toList(),
                exhibition.getExhibitionFiles().stream().map(fileInfo -> ExhibitionFileResponseDto.from(fileInfo.getFileInfo())).collect(Collectors.toList()),
                exhibition.getCreatedAt(),
                exhibition.getUpdatedAt()
        );
    }
}
