package org.example.exhibitiontimeslotbooking.dto.role.request;

import org.example.exhibitiontimeslotbooking.common.enums.RoleType;

public record RoleRequestDto(
        RoleType roleName
) {

}
