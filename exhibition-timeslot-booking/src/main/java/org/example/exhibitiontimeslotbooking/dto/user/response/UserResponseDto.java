package org.example.exhibitiontimeslotbooking.dto.user.response;

import lombok.Builder;
import lombok.Getter;
import org.example.exhibitiontimeslotbooking.entity.user.User;

@Getter
@Builder
public class UserResponseDto {
    private Long id;
    private String name;

    public static UserResponseDto of(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
