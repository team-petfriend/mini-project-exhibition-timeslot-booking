package org.example.exhibitiontimeslotbooking.dto.venues.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record VenuesCreateRequestDto(

        @NotBlank(message = "전시장 이름은 필수입니다.")
        @Size(max = 100, message = "이름은 100자를 넘길 수 없습니다.")
        String name,

        String address,

        BigDecimal latitude,

        BigDecimal longitude
) {

}
