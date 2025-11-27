package org.example.exhibitiontimeslotbooking.controller.user;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.user.request.UserProfileUpdateRequest;
import org.example.exhibitiontimeslotbooking.dto.user.response.MeResponseDto;
import org.example.exhibitiontimeslotbooking.dto.user.response.UserResponseDto;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.security.user.UserPrincipal;
import org.example.exhibitiontimeslotbooking.service.file.ProfileServiceImpl;
import org.example.exhibitiontimeslotbooking.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(ApiMappingPattern.Users.ROOT)
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

    @GetMapping(ApiMappingPattern.Users.ID_ONLY)
    public ResponseEntity<ResponseDto<UserResponseDto>> getById(
            @PathVariable Long userId
    ) {
        ResponseDto<UserResponseDto> result = userService.getUserById(userId);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @PostMapping(ApiMappingPattern.Users.PROFILE_IMAGE)
    public ResponseEntity<ResponseDto<?>> uploadProfile(
            @AuthenticationPrincipal Long userId,
            @RequestParam("file") MultipartFile file
    ) {
        ResponseDto<FileInfo> result = profileService.updateProfile(userId, file);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @PutMapping(ApiMappingPattern.Users.PROFILE)
    public ResponseEntity<ResponseDto<UserResponseDto>> updateProfile(
            @RequestAttribute("userId") Long userId,
            @RequestBody UserProfileUpdateRequest request
    ) {
        ResponseDto<UserResponseDto> result = userService.updateProfile(userId, request);
        return ResponseEntity.status(result.getStatus()).body(result);
    }
}
