package org.example.exhibitiontimeslotbooking.dto.exhibitions.response;

import jakarta.persistence.*;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.CapacityPolicy;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.ExhibitionStatus;
import org.example.exhibitiontimeslotbooking.dto.exbitions_file.response.ExhibitionFileDto;
import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;
import org.example.exhibitiontimeslotbooking.entity.file.ExhibitionFile;
import org.example.exhibitiontimeslotbooking.entity.timeslot.Timeslot;
import org.example.exhibitiontimeslotbooking.entity.venue.Venue;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record ExhibitionDetailResponse(
        Long id,
        String title,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        ExhibitionStatus exhibitionStatus,
        CapacityPolicy capacityPolicy,
        List<Timeslot> timeslots,
        List<ExhibitionFileDto> exhibitionsFiles,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ExhibitionDetailResponse from( Exhibition exhibition ) {

        if (exhibition == null) return null;

        return new ExhibitionDetailResponse(
                exhibition.getId(),
                exhibition.getTitle(),
                exhibition.getDescription(),
                exhibition.getStartDate(),
                exhibition.getEndDate(),
                exhibition.getExhibitionStatus(),
                exhibition.getCapacityPolicy(),
                exhibition.getTimeslots().stream().toList(),
                exhibition.getExhibitionFiles().stream().map(fileInfo -> ExhibitionFileDto.from(fileInfo.getFileInfo())).collect(Collectors.toList()),
                exhibition.getCreatedAt(),
                exhibition.getUpdatedAt()
        );
    }
}
