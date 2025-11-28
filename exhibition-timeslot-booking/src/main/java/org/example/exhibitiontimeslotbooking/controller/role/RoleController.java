package org.example.exhibitiontimeslotbooking.controller.role;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.example.exhibitiontimeslotbooking.dto.role.request.RoleRequestDto;
import org.example.exhibitiontimeslotbooking.dto.role.response.RoleResponseDto;
import org.example.exhibitiontimeslotbooking.repository.user.RoleRepository;
import org.example.exhibitiontimeslotbooking.service.role.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.Roles.ROOT)
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @PostMapping(ApiMappingPattern.Roles.GRANT)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> addRoleToUser(
            @PathVariable String userId,
            @RequestBody RoleRequestDto request
    ) {
        roleService.addRoleToUser(userId, request.roleName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(ApiMappingPattern.Roles.COLLECT)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> removeRoleFromUser(
            @PathVariable Long userId,
            @PathVariable String roleName
    ) {
        roleService.removeRoleFromUser(userId, roleName);
        return ResponseEntity.ok().build();
    }
}
