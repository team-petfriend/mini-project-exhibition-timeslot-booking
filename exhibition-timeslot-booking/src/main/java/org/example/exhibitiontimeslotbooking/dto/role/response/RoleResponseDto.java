package org.example.exhibitiontimeslotbooking.dto.role.response;

import org.example.exhibitiontimeslotbooking.entity.user.Role;
import org.example.exhibitiontimeslotbooking.entity.user.User;

import java.util.List;
import java.util.stream.Collectors;

public record RoleResponseDto(
        String roleName,
        List<User> users
) {
    public record User(String loginId, String name) {}

    public static RoleResponseDto from(Role role) {
        List<User> users = role.getUserRoles().stream()
                .map(userRole -> new User(
                        userRole.getUser().getLoginId(),
                        userRole.getUser().getName()
                ))
                .collect(Collectors.toList());

        return new RoleResponseDto(role.getName().name(), users);
    }
}
