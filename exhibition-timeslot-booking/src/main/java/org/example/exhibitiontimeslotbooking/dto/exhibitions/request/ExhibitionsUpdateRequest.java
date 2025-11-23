package org.example.exhibitiontimeslotbooking.dto.exhibitions.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.CapacityPolicy;

import java.time.LocalDateTime;

public record ExhibitionsUpdateRequest(

        String title,

        String description,

        LocalDateTime startDate,

        LocalDateTime endDate,

        CapacityPolicy capacityPolicy
) {
}
