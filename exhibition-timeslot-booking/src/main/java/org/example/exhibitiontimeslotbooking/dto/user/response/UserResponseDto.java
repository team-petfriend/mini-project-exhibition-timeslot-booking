package org.example.exhibitiontimeslotbooking.dto.user.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String profileImageUrl;
}
