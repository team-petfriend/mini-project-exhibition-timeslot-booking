package org.example.exhibitiontimeslotbooking.dto.exhibitions.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;

public record ExhibitionsCreateRequestDto(
        @NotNull (message = "venue_Id는 필수입니다.")
        Long venueId,

        @NotBlank(message = "제목은 비워둘수 없습니다.")
        String title,

        String description,

        @NotNull(message = "시작시간은 비워둘 수 없습니다.")
        LocalDate startDate,

        @NotNull(message = "종료시간은 필수입니다.")
        LocalDate endDate
) {
}
