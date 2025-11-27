package org.example.exhibitiontimeslotbooking.service.user;

import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.user.request.UserProfileUpdateRequest;
import org.example.exhibitiontimeslotbooking.dto.user.response.MeResponseDto;
import org.example.exhibitiontimeslotbooking.dto.user.response.UserResponseDto;

public interface UserService {
    ResponseDto<MeResponseDto> getMe(Long id);

    ResponseDto<UserResponseDto> getUserById(Long userId);

    ResponseDto<UserResponseDto> updateProfile(Long userId, UserProfileUpdateRequest request);
}
