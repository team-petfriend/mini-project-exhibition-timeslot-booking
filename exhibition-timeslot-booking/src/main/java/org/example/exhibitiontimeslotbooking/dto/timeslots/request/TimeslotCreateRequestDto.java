package org.example.exhibitiontimeslotbooking.dto.timeslots.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TimeslotCreateRequestDto(
        @NotNull(message = "시작시간은 필수입니다.")
        LocalDateTime startTime,

        @NotNull(message = "끝나는 시간은 필수입니다.")
        LocalDateTime endTime,

        @NotNull(message = "인원은 필수입니다.")
        int  capacity,

        @NotNull(message = "예약인원 필수입니다.")
        int reserved
) {
}
