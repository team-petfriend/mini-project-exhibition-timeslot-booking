package org.example.exhibitiontimeslotbooking.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.user.request.UserProfileUpdateRequest;
import org.example.exhibitiontimeslotbooking.dto.user.response.MeResponseDto;
import org.example.exhibitiontimeslotbooking.dto.user.response.UserResponseDto;
import org.example.exhibitiontimeslotbooking.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public ResponseDto<MeResponseDto> getMe(Long id) {
        return null;
    }

    @Override
    public ResponseDto<UserResponseDto> getUserById(Long userId) {
        return null;
    }

    @Override
    public ResponseDto<UserResponseDto> updateProfile(Long userId, UserProfileUpdateRequest request) {
        return null;
    }
}
