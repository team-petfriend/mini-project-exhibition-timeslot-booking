package org.example.exhibitiontimeslotbooking.repository.auth;

import org.example.exhibitiontimeslotbooking.entity.auth.RefreshToken;
import org.example.exhibitiontimeslotbooking.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUser(User user);
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);
}
