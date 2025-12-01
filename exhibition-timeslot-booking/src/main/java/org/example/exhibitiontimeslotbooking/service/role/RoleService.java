package org.example.exhibitiontimeslotbooking.service.role;

import org.example.exhibitiontimeslotbooking.common.enums.RoleType;
import org.example.exhibitiontimeslotbooking.dto.role.response.RoleResponseDto;

import java.util.List;

public interface RoleService {

    List<RoleResponseDto> getAllRoles();

    void addRoleToUser(long userId, RoleType roleName);

    void removeRoleFromUser(Long userId, RoleType roleName);
}
