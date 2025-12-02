package org.example.exhibitiontimeslotbooking.dto.user.request;

import org.example.exhibitiontimeslotbooking.common.enums.RoleType;

import java.util.Set;

public record AdminUserUpdateRequestDto(
        String name,
        String email,
        Set<RoleType> roles
) {
}
