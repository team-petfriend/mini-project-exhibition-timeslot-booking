package org.example.exhibitiontimeslotbooking.controller.user;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.example.exhibitiontimeslotbooking.common.enums.RoleType;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.user.request.AdminUserUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.user.request.UserMeUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.user.response.MeResponseDto;
import org.example.exhibitiontimeslotbooking.dto.user.response.UserResponseDto;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.security.user.UserPrincipal;
import org.example.exhibitiontimeslotbooking.service.file.ProfileServiceImpl;
import org.example.exhibitiontimeslotbooking.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProfileServiceImpl profileService;

    @GetMapping(ApiMappingPattern.Users.ME)
    public ResponseEntity<ResponseDto<MeResponseDto>> me (
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        ResponseDto<MeResponseDto> result = userService.getMe(userPrincipal.getId());
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @PutMapping(ApiMappingPattern.Users.ME)
    public ResponseEntity<ResponseDto<UserResponseDto>> updateMe(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody UserMeUpdateRequestDto request
    ) {
        ResponseDto<UserResponseDto> result = userService.updateMe(userPrincipal.getId(), request);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @PostMapping(ApiMappingPattern.Users.PROFILE)
    public ResponseEntity<ResponseDto<?>> uploadProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam("file") MultipartFile file
    ) {
        ResponseDto<FileInfo> result = profileService.updateProfile(userPrincipal.getId(), file);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(ApiMappingPattern.Users.ROOT)
    public ResponseEntity<ResponseDto<?>> getUserList(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) RoleType role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort
    ) {
        ResponseDto<?> result = userService.getUsers(q, role, page, size, sort);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(ApiMappingPattern.Users.BY_ID)
    public ResponseEntity<ResponseDto<UserResponseDto>> getById(
            @PathVariable Long userId
    ) {
        ResponseDto<UserResponseDto> result = userService.getUserById(userId);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(ApiMappingPattern.Users.BY_ID)
    public ResponseEntity<ResponseDto<UserResponseDto>> adminUpdateUser(
            @PathVariable Long userId,
            @RequestBody AdminUserUpdateRequestDto request
    ) {
        ResponseDto<UserResponseDto> result = userService.adminUpdateUser(userId, request);
        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
