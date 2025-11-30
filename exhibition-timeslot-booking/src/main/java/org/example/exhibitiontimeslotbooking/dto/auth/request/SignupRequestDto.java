package org.example.exhibitiontimeslotbooking.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.exhibitiontimeslotbooking.common.enums.AuthProvider;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.entity.user.User;

public record SignupRequestDto(
        @NotBlank(message = "이름은 필수값입니다.")
        @Size(max = 50, message = "이름은 50자 내로 작성하세요")
        String name,

        @NotBlank(message = "아이디는 필수값입니다.")
        @Size(min=4, max = 50, message = "아이디는 4~50자 내로 작성하세요")
        String loginId,

        @NotBlank(message = "비밀번호는 필수값입니다.")
        @Size(min = 8, max = 100, message = "비밀번호는 최소 8자 이상이어야 합니다.")
        String password,

        @NotBlank(message = "이메일은 필수값입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,

        AuthProvider provider
) {
        public User toEntity(FileInfo profileFile) {
                return User.builder()
                        .name(name)
                        .loginId(loginId)
                        .password(password)
                        .email(email)
                        .profileFile(profileFile)
                        .build();
        }
}
