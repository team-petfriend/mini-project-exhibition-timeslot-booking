package org.example.exhibitiontimeslotbooking.service.auth.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.auth.request.SignupRequestDto;
import org.example.exhibitiontimeslotbooking.dto.auth.response.LoginResponseDto;
import org.example.exhibitiontimeslotbooking.dto.auth.response.SignupResponseDto;
import org.example.exhibitiontimeslotbooking.service.auth.AuthService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Override
    public ResponseDto<SignupResponseDto> signup(SignupRequestDto request) {
        return null;
    }

    @Override
    public ResponseDto<LoginResponseDto> login(LoginResponseDto request, HttpServletResponse response) {
        return null;
    }

    @Override
    public ResponseDto<LoginResponseDto> refreshAccessToken(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public ResponseDto<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
