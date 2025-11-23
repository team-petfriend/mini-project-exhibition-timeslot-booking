package org.example.exhibitiontimeslotbooking.dto.timeslots.request;

import org.example.exhibitiontimeslotbooking.common.enums.slots.SlotStatus;

import java.time.LocalDateTime;

public record TimeslotCreateRequest(
        LocalDateTime startTime,
        LocalDateTime endTime,
        int  capacity,
        int reserved,
        int count,
        SlotStatus timeslotstatus
) {
}
