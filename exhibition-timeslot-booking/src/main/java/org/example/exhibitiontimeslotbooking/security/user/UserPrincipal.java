package org.example.exhibitiontimeslotbooking.security.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.example.exhibitiontimeslotbooking.common.enums.RoleType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@ToString(exclude = "password")
public class UserPrincipal implements UserDetails, OAuth2User, Serializable {

    private final Long id;
    private final String loginId;

    @JsonIgnore
    private final String password;

    @JsonIgnore
    private final List<? extends GrantedAuthority> authorities;

    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    @Builder
    private UserPrincipal(
            Long id,
            String loginId,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            boolean accountNonExpired,
            boolean accountNonLocked,
            boolean credentialsNonExpired,
            boolean enabled
    ) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.authorities = List.copyOf(authorities);
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    // OAuth2User 전용 필드
    private Map<String, Object> attributes;
    private String userId;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return userId;
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
    @Override public String getUsername() { return loginId; }
    @Override public String getPassword() { return password; }
    @Override public boolean isAccountNonExpired() { return accountNonExpired; }
    @Override public boolean isAccountNonLocked() { return accountNonLocked; }
    @Override public boolean isCredentialsNonExpired() { return credentialsNonExpired; }
    @Override public boolean isEnabled() { return enabled; }

    public List<String> getRolesWithoutPrefix() {
        return this.getAuthorities().stream()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .toList();
    }

    public boolean hasRole(RoleType roleType) {
        String targetRole = roleType.name();
        return authorities.stream()
                .anyMatch(a -> a.getAuthority().replace("ROLE_", "").equals(targetRole));
    }
}
