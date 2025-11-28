package org.example.exhibitiontimeslotbooking.service.role;

import org.example.exhibitiontimeslotbooking.dto.role.response.RoleResponseDto;

import java.util.List;

public interface RoleService {
    List<RoleResponseDto> getAllRoles();

    void addRoleToUser(String userId, String roleName);

    void removeRoleFromUser(Long userId, String roleName);
}
