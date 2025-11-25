package org.example.exhibitiontimeslotbooking.security.user;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.entity.user.User;
import org.example.exhibitiontimeslotbooking.repository.user.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserPrincipalMapper {
    private final UserRepository userRepository;

    public UserPrincipal toPrincipal(@NonNull String loginId) {

        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("User not found: " + loginId));

        return map(user);
    }

    public UserPrincipal map(@NonNull User user) {

        List<SimpleGrantedAuthority> authorities =
                (user.getUserRoles() == null || user.getUserRoles().isEmpty())
                        ? List.of(new SimpleGrantedAuthority("ROLE_USER"))
                        : user.getUserRoles().stream()
                        .map(role -> {
                            String r = role.getRole().getName().name();
                            String name = r.startsWith("ROLE_") ? r : "ROLE_" + r;
                            return new SimpleGrantedAuthority(name);
                        })
                        .toList();

        return UserPrincipal.builder()
                .id(user.getId())
                .loginId(user.getLoginId())
                .password(user.getPassword())
                .authorities(authorities)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }
}
