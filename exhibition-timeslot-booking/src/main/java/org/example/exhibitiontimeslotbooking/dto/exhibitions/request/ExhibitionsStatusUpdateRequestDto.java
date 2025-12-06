package org.example.exhibitiontimeslotbooking.dto.exhibitions.request;

import jakarta.validation.constraints.NotBlank;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.ExhibitionStatus;

public record ExhibitionsStatusUpdateRequestDto(
        @NotBlank(message = "상태값은 비워둘 수 없습니다.")
        ExhibitionStatus exhibitionStatus
) {
}
