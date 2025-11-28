package org.example.exhibitiontimeslotbooking.dto.user.request;

import lombok.Getter;
import org.example.exhibitiontimeslotbooking.common.enums.RoleType;

import java.util.Set;

@Getter
public class AdminUserUpdateRequest {

    private String name;
    private String email;
    private Boolean enabled;
    private Set<RoleType> roles;
}
