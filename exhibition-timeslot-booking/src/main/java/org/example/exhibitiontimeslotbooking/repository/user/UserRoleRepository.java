package org.example.exhibitiontimeslotbooking.repository.user;

import org.example.exhibitiontimeslotbooking.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByUserIdAndRoleName(Long userId, String roleName);
}
