package org.example.exhibitiontimeslotbooking.dto.user.response;

import org.example.exhibitiontimeslotbooking.entity.user.User;

public record UserResponseDto(
        Long id,
        String name,
        String email
) {
    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail());
    }
}
