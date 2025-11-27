package org.example.exhibitiontimeslotbooking.dto.timeslots.request;

import jakarta.validation.constraints.NotNull;
import org.example.exhibitiontimeslotbooking.common.enums.slots.SlotStatus;

import java.time.LocalDateTime;

public record TimeslotUpdateRequestDto(
        LocalDateTime startTime,
        LocalDateTime endTime,
        int capacity
) {
}
