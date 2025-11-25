package org.example.exhibitiontimeslotbooking.security.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.errors.ErrorCode;
import org.example.exhibitiontimeslotbooking.exception.BusinessException;
import org.example.exhibitiontimeslotbooking.security.user.UserPrincipal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PrincipalUtils {
    public static void validateActive(UserPrincipal principal) {

        if (principal == null) {
            throw new BusinessException(ErrorCode.INVALID_AUTH);
        }

        if (!principal.isEnabled()) {
            throw new BusinessException(ErrorCode.ACCESS_DENIED);
        }

        if (!principal.isAccountNonLocked()) {
            throw new BusinessException(ErrorCode.ACCESS_DENIED);
        }

        if (!principal.isAccountNonExpired()) {
            throw new BusinessException(ErrorCode.ACCESS_DENIED);
        }

        if (!principal.isCredentialsNonExpired()) {
            throw new BusinessException(ErrorCode.ACCESS_DENIED);
        }
    }
}
