package org.example.exhibitiontimeslotbooking.service.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.auth.request.LoginRequestDto;
import org.example.exhibitiontimeslotbooking.dto.auth.request.SignupRequestDto;
import org.example.exhibitiontimeslotbooking.dto.auth.response.LoginResponseDto;
import org.example.exhibitiontimeslotbooking.dto.auth.response.SignupResponseDto;

public interface AuthService {
    ResponseDto<SignupResponseDto> signup(@Valid SignupRequestDto request);

    ResponseDto<LoginResponseDto> login(@Valid LoginRequestDto request, HttpServletResponse response);

    ResponseDto<LoginResponseDto> refreshAccessToken(HttpServletRequest request, HttpServletResponse response);

    ResponseDto<Void> logout(HttpServletRequest request, HttpServletResponse response);
}
