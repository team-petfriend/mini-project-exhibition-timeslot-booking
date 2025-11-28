package org.example.exhibitiontimeslotbooking.repository.user;

import org.example.exhibitiontimeslotbooking.common.enums.RoleType;
import org.example.exhibitiontimeslotbooking.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, RoleType> {
    Optional<Role> findByName(RoleType name);
}
