package org.example.exhibitiontimeslotbooking.dto.timeslots.request;

import org.example.exhibitiontimeslotbooking.common.enums.slots.SlotStatus;

public record TimeslotStatusChangeRequestDto(
        SlotStatus timeslotstatus
) {
}
