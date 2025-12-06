package org.example.exhibitiontimeslotbooking.dto.exhibitions.request;

import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.CapacityPolicy;
import java.time.LocalDate;

public record ExhibitionsUpdateRequestDto(
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        CapacityPolicy capacityPolicy
//        List<Long> addFileIds,
//        List<Long> deleteFileIds,
//        List<Long> updateFileIds
) {
}
