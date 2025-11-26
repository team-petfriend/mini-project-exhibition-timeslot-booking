package org.example.exhibitiontimeslotbooking.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.AuthProvider;
import org.example.exhibitiontimeslotbooking.common.enums.RoleType;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.entity.review.Review;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_login", columnNames = "login_id"),
                @UniqueConstraint(name = "uk_email", columnNames = "email"),
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", updatable = false, nullable = false, length = 50)
    private String name;

    @Column(name = "login_id", nullable = false, length = 50)
    private String loginId;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_file_id",
        foreignKey = @ForeignKey(name = "fk_users_profile_file"))
    private FileInfo profileFile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", length = 20, nullable = false)
    private AuthProvider provider;

    @Column(name = "provider_id", length = 100)
    private String providerId;

    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified;

    @Builder
    private User(String name, String loginId, String password, String email, FileInfo profileFile) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.profileFile = profileFile;
    }

    public void updateProfile(String name) {
        this.name = name;
    }

    public void updateProfileImage(FileInfo newProfileFile) {
        this.profileFile= newProfileFile;
    }

    public void grantRole(Role role) {
        boolean exists = userRoles.stream()
                .anyMatch(userRole -> userRole.getRole().equals(role));
        if (!exists) {
            userRoles.add(new UserRole(this, role));
        }
    }

    public void revokeRole(Role role) {
        userRoles.removeIf(userRole -> userRole.getRole().equals(role));
    }

    public Set<RoleType> getRoleTypes() {
        return userRoles.stream()
                .map(UserRole::getRole)
                .map(Role::getName)
                .collect(Collectors.toUnmodifiableSet());
    }



}
