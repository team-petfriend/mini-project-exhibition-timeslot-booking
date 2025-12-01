package org.example.exhibitiontimeslotbooking.service.role.impl;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.RoleType;
import org.example.exhibitiontimeslotbooking.dto.role.response.RoleResponseDto;
import org.example.exhibitiontimeslotbooking.entity.user.Role;
import org.example.exhibitiontimeslotbooking.entity.user.User;
import org.example.exhibitiontimeslotbooking.entity.user.UserRole;
import org.example.exhibitiontimeslotbooking.repository.user.RoleRepository;
import org.example.exhibitiontimeslotbooking.repository.user.UserRepository;
import org.example.exhibitiontimeslotbooking.repository.user.UserRoleRepository;
import org.example.exhibitiontimeslotbooking.service.role.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;


    @Override
    public List<RoleResponseDto> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(RoleResponseDto::from)
                .toList();
    }

    @Override
    @Transactional
    public void addRoleToUser(long userId, RoleType roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저가 없습니다"));

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("해당 역할이 없습니다"));

        userRoleRepository.save(new UserRole(user, role));
    }


    @Override
    @Transactional
    public void removeRoleFromUser(Long userId, RoleType roleName) {
        UserRole userRole = userRoleRepository
                .findByUserIdAndRoleName(userId, roleName)
                .orElseThrow(() -> new RuntimeException("해당 유저의 역할이 없습니다."));

        userRoleRepository.delete(userRole);
    }
}
