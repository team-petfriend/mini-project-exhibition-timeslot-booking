package org.example.exhibitiontimeslotbooking.service.user.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.RoleType;
import org.example.exhibitiontimeslotbooking.common.enums.errors.ErrorCode;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.user.request.AdminUserUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.user.request.UserMeUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.user.response.MeResponseDto;
import org.example.exhibitiontimeslotbooking.dto.user.response.UserResponseDto;
import org.example.exhibitiontimeslotbooking.entity.user.Role;
import org.example.exhibitiontimeslotbooking.entity.user.User;
import org.example.exhibitiontimeslotbooking.exception.BusinessException;
import org.example.exhibitiontimeslotbooking.repository.user.RoleRepository;
import org.example.exhibitiontimeslotbooking.repository.user.UserRepository;
import org.example.exhibitiontimeslotbooking.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<MeResponseDto> getMe(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        MeResponseDto dto = MeResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .roles(user.getUserRoles().stream()
                        .map(role -> role.getRole().getName()).collect(Collectors.toSet()))
                .provider(user.getProviderId())
                .build();

        return ResponseDto.success(dto);
    }

    @Override
    @Transactional
    public ResponseDto<UserResponseDto> updateMe(Long id, UserMeUpdateRequestDto request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        user.updateProfile(request.name(), request.email());

        User updated = userRepository.save(user);

        UserResponseDto dto = UserResponseDto.of(updated);

        return ResponseDto.success(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<?> getUsers(String q, RoleType role, int page, int size, String sort) {
        String[] sortParams = sort.split(",");
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(
                "asc".equalsIgnoreCase(sortParams[1])
                        ? Sort.Direction.ASC
                        :Sort.Direction.DESC,
                sortParams[0]
        ));

        Page<User> users = userRepository.searchUsers(q, role, pageRequest);

        Page<UserResponseDto> dto = users.map(UserResponseDto::of);

        return ResponseDto.success(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<UserResponseDto> getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        return ResponseDto.success(UserResponseDto.of(user));
    }

    @Override
    @Transactional
    public ResponseDto<UserResponseDto> adminUpdateUser(Long userId, AdminUserUpdateRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        user.updateProfile(request.name(), request.email());

        if (request.roles() != null && !request.roles().isEmpty()) {
            Set<Role> roles = request.roles().stream()
                    .map(roleType -> roleRepository.findByName(roleType)
                            .orElseThrow(() -> new EntityNotFoundException("해당 역할을 찾을 수 없습니다: " + roleType)))
                    .collect(Collectors.toSet());

            user.updateRoles(roles);
        }

        userRepository.save(user);

        return ResponseDto.success(UserResponseDto.of(user));
    }
}
