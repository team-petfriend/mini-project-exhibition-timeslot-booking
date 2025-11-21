package org.example.exhibitiontimeslotbooking.dto.auth.request;

import jakarta.validation.constraints.NotBlank;

public record LogoutRequestDto(
        @NotBlank(message = "Refresh Token은 필수값입니다.")
        String refreshToken
) {
}
