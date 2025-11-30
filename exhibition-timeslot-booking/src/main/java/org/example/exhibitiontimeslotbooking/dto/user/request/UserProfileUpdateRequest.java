package org.example.exhibitiontimeslotbooking.dto.user.request;

import jakarta.validation.constraints.NotBlank;

public record UserProfileUpdateRequest(
        @NotBlank(message = "이름은 필수입니다.")
        String name
) {
}
