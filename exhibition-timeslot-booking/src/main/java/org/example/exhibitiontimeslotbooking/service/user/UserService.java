package org.example.exhibitiontimeslotbooking.service.user;

import org.example.exhibitiontimeslotbooking.common.enums.RoleType;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.user.request.AdminUserUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.user.request.UserMeUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.user.response.MeResponseDto;
import org.example.exhibitiontimeslotbooking.dto.user.response.UserResponseDto;

public interface UserService {

    ResponseDto<MeResponseDto> getMe(Long id);

    ResponseDto<UserResponseDto> updateMe(Long id, UserMeUpdateRequestDto request);

    ResponseDto<?> getUsers(String q, RoleType role, int page, int size, String sort);

    ResponseDto<UserResponseDto> getUserById(Long userId);

    ResponseDto<UserResponseDto> adminUpdateUser(Long userId, AdminUserUpdateRequestDto request);
}
