package org.example.exhibitiontimeslotbooking.repository.user;

import org.example.exhibitiontimeslotbooking.common.enums.AuthProvider;
import org.example.exhibitiontimeslotbooking.common.enums.RoleType;
import org.example.exhibitiontimeslotbooking.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);

    Optional<User> findByProviderAndProviderId(AuthProvider provider, String providerId);

    @Query("""
        select distinct u
        from User u
        left join fetch u.userRoles ur
            left join fetch ur.role r
        where u.name = :username
    """)
    Optional<User> findWithRolesByUsername(@Param("username") String username);

    @Query("""
        select u
        from User u
        left join fetch u.userRoles ur
        left join fetch ur.role r
        where (:q is null or u.name like %:q%)
        and (:role is null or r.name = :role)
    """)
    Page<User> searchUsers(@Param("q") String q, @Param("role") RoleType role, Pageable pageable);
}
