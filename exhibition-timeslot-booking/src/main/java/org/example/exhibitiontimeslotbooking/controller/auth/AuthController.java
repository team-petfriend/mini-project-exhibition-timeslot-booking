package org.example.exhibitiontimeslotbooking.controller.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.auth.request.LoginRequestDto;
import org.example.exhibitiontimeslotbooking.dto.auth.request.SignupRequestDto;
import org.example.exhibitiontimeslotbooking.dto.auth.response.LoginResponseDto;
import org.example.exhibitiontimeslotbooking.dto.auth.response.SignupResponseDto;
import org.example.exhibitiontimeslotbooking.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(ApiMappingPattern.Auth.SIGNUP)
    public ResponseEntity<ResponseDto<SignupResponseDto>> signup(
            @Valid @RequestBody SignupRequestDto request
    ) {
        ResponseDto<SignupResponseDto> response = authService.signup(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping(ApiMappingPattern.Auth.LOGIN)
    public ResponseEntity<ResponseDto<LoginResponseDto>> login(
            @Valid @RequestBody LoginRequestDto request,
            HttpServletResponse response
    ) {
        ResponseDto<LoginResponseDto> result = authService.login(request, response);
        return ResponseEntity.status(response.getStatus()).body(result);
    }

    @PostMapping(ApiMappingPattern.Auth.REFRESH)
    public ResponseEntity<ResponseDto<LoginResponseDto>> refresh(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        ResponseDto<LoginResponseDto> result = authService.refreshAccessToken(request, response);
        return ResponseEntity.status(response.getStatus()).body(result);
    }

    @PostMapping(ApiMappingPattern.Auth.LOGOUT)
    public ResponseEntity<ResponseDto<Void>> logout(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        ResponseDto<Void> result = authService.logout(request, response);
        return ResponseEntity.status(response.getStatus()).body(result);
    }
}
