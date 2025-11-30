package org.example.exhibitiontimeslotbooking.dto.user.request;

import org.example.exhibitiontimeslotbooking.common.enums.RoleType;

import java.util.Set;

public record AdminUserUpdateRequest(
        String name,
        String email,
        Boolean enabled,
        Set<RoleType> roles
) {
}
