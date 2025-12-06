package org.example.exhibitiontimeslotbooking.dto.exhibitions.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public record ExhibitionsCreateRequestDto(
        @NotBlank(message = "제목은 비워둘수 없습니다.")
        String title,

        String description,

        @NotNull(message = "시작시간은 비워둘 수 없습니다.")
        LocalDate startDate,

        @NotNull(message = "종료시간은 필수입니다.")
        LocalDate endDate,

        @NotNull(message = "파일 id는 필수 입니다.")
        List<Long> fileIds
) {
}
