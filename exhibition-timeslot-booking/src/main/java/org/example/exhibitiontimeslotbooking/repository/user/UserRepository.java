package org.example.exhibitiontimeslotbooking.repository.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.example.exhibitiontimeslotbooking.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserLoginId(String userId);
    Optional<User> findByEmail(@NotBlank(message = "이메일은 필수입니다.") @Email(message = "이메일 형식이 올바르지 않습니다.") String email);
}
