package org.example.exhibitiontimeslotbooking.dto.role.response;

import org.example.exhibitiontimeslotbooking.entity.user.Role;

public record RoleResponseDto(
        String roleName
) {
    public static RoleResponseDto from(Role role) {
        return new RoleResponseDto(role.getName().name());
    }
}
