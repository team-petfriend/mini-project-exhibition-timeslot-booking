package org.example.exhibitiontimeslotbooking.dto.timeslots.response;

import org.example.exhibitiontimeslotbooking.common.enums.slots.SlotStatus;
import org.example.exhibitiontimeslotbooking.entity.timeslot.Timeslot;

import java.time.LocalDateTime;

public record TimeslotDetailResponse(
        Long exhibitionId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        int capacity,
        int reserved,
        SlotStatus timeslotstatus
) {
    public static TimeslotDetailResponse from(Timeslot timeslot) {

        if (timeslot == null) return null;

        return new TimeslotDetailResponse(
                timeslot.getExhibition().getId(),
                timeslot.getStartTime(),
                timeslot.getEndTime(),
                timeslot.getCapacity(),
                timeslot.getReserved(),
                timeslot.getSlotsStatus()
        );
    }
}
