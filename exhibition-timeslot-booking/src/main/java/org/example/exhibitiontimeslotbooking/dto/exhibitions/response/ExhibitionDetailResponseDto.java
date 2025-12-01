package org.example.exhibitiontimeslotbooking.dto.exhibitions.response;

import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.CapacityPolicy;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.ExhibitionStatus;
import org.example.exhibitiontimeslotbooking.dto.exbitions_file.response.ExhibitionFileResponseDto;
import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;
import org.example.exhibitiontimeslotbooking.entity.file.ExhibitionFile;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.entity.timeslot.Timeslot;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;

import java.util.Objects;
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
        List<String> exhibitionImgURL,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ExhibitionDetailResponseDto from(Exhibition exhibition ) {

        if (exhibition == null) return null;

        List<String> exhibitionImgURL = exhibition.getExhibitionFiles().stream()
                .map(ExhibitionFile::getFileInfo)
                 .filter(Objects::nonNull)
                 .map(fileInfo -> "/upload/" + fileInfo.getFilePath().replace("\\", "/"))
                 .toList();

        return new ExhibitionDetailResponseDto(
                exhibition.getId(),
                exhibition.getTitle(),
                exhibition.getDescription(),
                exhibition.getStartDate(),
                exhibition.getEndDate(),
                exhibition.getExhibitionStatus(),
                exhibition.getCapacityPolicy(),
                exhibition.getTimeslots().stream().toList(),
                exhibitionImgURL,
                exhibition.getCreatedAt(),
                exhibition.getUpdatedAt()
        );
    }
}
