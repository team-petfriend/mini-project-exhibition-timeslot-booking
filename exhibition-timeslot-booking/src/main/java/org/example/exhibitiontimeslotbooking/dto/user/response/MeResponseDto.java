package org.example.exhibitiontimeslotbooking.dto.user.response;

import lombok.Builder;
import lombok.Getter;
import org.example.exhibitiontimeslotbooking.common.enums.RoleType;

import java.util.Set;

@Getter
@Builder
public class MeResponseDto {
    private Long id;
    private String name;
    private String email;
    private String profileImageUrl;
    private Set<RoleType> roles;
    private String provider;
}
