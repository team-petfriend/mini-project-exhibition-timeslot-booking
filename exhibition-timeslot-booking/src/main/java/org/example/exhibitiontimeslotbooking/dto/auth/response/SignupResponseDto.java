package org.example.exhibitiontimeslotbooking.dto.auth.response;

import org.example.exhibitiontimeslotbooking.entity.user.User;

public record SignupResponseDto(
        String name,
        String loginId,
        String email
) {
    public static SignupResponseDto from(User user) {
        return new SignupResponseDto(
                user.getName(),
                user.getLoginId(),
                user.getEmail()
        );
    }
}
