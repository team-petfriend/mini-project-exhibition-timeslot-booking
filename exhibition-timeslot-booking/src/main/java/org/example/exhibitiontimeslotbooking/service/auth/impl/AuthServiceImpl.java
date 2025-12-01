package org.example.exhibitiontimeslotbooking.service.auth.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exhibitiontimeslotbooking.common.enums.RoleType;
import org.example.exhibitiontimeslotbooking.common.enums.errors.ErrorCode;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.auth.request.LoginRequestDto;
import org.example.exhibitiontimeslotbooking.dto.auth.request.SignupRequestDto;
import org.example.exhibitiontimeslotbooking.dto.auth.response.LoginResponseDto;
import org.example.exhibitiontimeslotbooking.dto.auth.response.SignupResponseDto;
import org.example.exhibitiontimeslotbooking.entity.auth.RefreshToken;
import org.example.exhibitiontimeslotbooking.entity.user.Role;
import org.example.exhibitiontimeslotbooking.entity.user.User;
import org.example.exhibitiontimeslotbooking.entity.user.UserRole;
import org.example.exhibitiontimeslotbooking.exception.BusinessException;
import org.example.exhibitiontimeslotbooking.repository.auth.RefreshTokenRepository;
import org.example.exhibitiontimeslotbooking.repository.user.RoleRepository;
import org.example.exhibitiontimeslotbooking.repository.user.UserRepository;
import org.example.exhibitiontimeslotbooking.repository.user.UserRoleRepository;
import org.example.exhibitiontimeslotbooking.security.provider.JwtProvider;
import org.example.exhibitiontimeslotbooking.security.user.UserPrincipalMapper;
import org.example.exhibitiontimeslotbooking.security.util.CookieUtils;
import org.example.exhibitiontimeslotbooking.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserPrincipalMapper userPrincipalMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Value("${app.oauth2.authorized-redirect-uri}")
    private String redirectUri;

    private static final String REFRESH_TOKEN = "refreshToken";

    @Override
    @Transactional
    public ResponseDto<SignupResponseDto> signup(SignupRequestDto request) {
        if (userRepository.findByLoginId(request.loginId()).isPresent()) {
            throw new BusinessException(ErrorCode.DUPLICATE_USER);
        }

        User newUser = User.builder()
                .name(request.name())
                .loginId(request.loginId())
                .password(passwordEncoder.encode(request.password()))
                .email(request.email())
                .provider(request.provider())
                .build();

        userRepository.save(newUser);

        Role userRole = roleRepository.findByName(RoleType.USER)
                        .orElseThrow(() -> new RuntimeException("권한을 찾지 못하였습니다."));

        UserRole newUserRole = new UserRole(newUser, userRole);
        userRoleRepository.save(newUserRole);

        return ResponseDto.success(
                "회원가입 완료",
                SignupResponseDto.from(newUser)
        );
    }

    @Override
    @Transactional
    public ResponseDto<LoginResponseDto> login(LoginRequestDto request, HttpServletResponse response) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(
                    request.loginId(), request.password()
            );

            var authentication = authenticationManager.authenticate(authToken);

            String loginId = authentication.getName();

            var principal = userPrincipalMapper.toPrincipal(loginId);
            Set<String> roles = principal.getAuthorities()
                    .stream()
                    .map(a -> a.getAuthority())
                    .collect(java.util.stream.Collectors.toSet());

            String accessToken = jwtProvider.generateAccessToken(loginId, roles);
            String refreshToken = jwtProvider.generateRefreshToken(loginId, roles);

            long accessExpiresIn = jwtProvider.getRemainingMillis(accessToken);
            long refreshRemaining = jwtProvider.getRemainingMillis(refreshToken);

            Instant refreshExpiry = Instant.now().plusMillis(refreshRemaining);

            User user = userRepository.findByLoginId(loginId)
                    .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

            refreshTokenRepository.findByUser(user)
                    .ifPresentOrElse(
                            r -> {
                                r.renew(refreshToken, refreshExpiry);
                                refreshTokenRepository.save(r);
                            },
                            () -> {
                                RefreshToken r = RefreshToken.builder()
                                        .user(user)
                                        .token(refreshToken)
                                        .expiry(refreshExpiry)
                                        .build();
                                refreshTokenRepository.save(r);
                            }
                    );

            CookieUtils.addHttpOnlyCookie(
                    response,
                    REFRESH_TOKEN,
                    refreshToken,
                    (int) (refreshRemaining / 1000),
                    true
            );

            return ResponseDto.success(
                    "로그인 성공",
                    LoginResponseDto.of(accessToken, accessExpiresIn)
            );

        } catch (BadCredentialsException ex) {
            throw new BusinessException(ErrorCode.AUTHENTICATION_FAILED);
        } catch (Exception ex) {
            throw new BusinessException(ErrorCode.INTERNAL_ERROR, ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseDto<LoginResponseDto> refreshAccessToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = CookieUtils.getCookie(request, REFRESH_TOKEN)
                .map(Cookie::getValue)
                .orElseThrow(() -> new BusinessException(ErrorCode.TOKEN_EXPIRED));

        if (!jwtProvider.isValidToken(refreshToken)) {
            throw new BusinessException(ErrorCode.TOKEN_INVALID);
        }

        String userId = jwtProvider.getUsernameFromJwt(refreshToken);

        User user = userRepository.findByLoginId(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        RefreshToken stored = refreshTokenRepository.findByUser(user)
                .orElseThrow(() -> new BusinessException(ErrorCode.TOKEN_INVALID));

        if (!stored.getToken().equals(refreshToken)) {
            throw new BusinessException(ErrorCode.TOKEN_INVALID, "Refresh token mismatch");
        }

        if (stored.isExpired()) {
            throw new BusinessException(ErrorCode.TOKEN_EXPIRED);
        }

        var principal = userPrincipalMapper.map(user);
        Set<String> roles = principal.getAuthorities()
                .stream().map(a -> a.getAuthority()).collect(java.util.stream.Collectors.toSet());

        String newAccess = jwtProvider.generateAccessToken(userId, roles);
        String newRefresh = jwtProvider.generateRefreshToken(userId, roles);

        long accessExpiresIn = jwtProvider.getRemainingMillis(newAccess);
        long refreshRemaining = jwtProvider.getRemainingMillis(newRefresh);

        stored.renew(newRefresh, Instant.now().plusMillis(refreshRemaining));
        refreshTokenRepository.save(stored);

        CookieUtils.addHttpOnlyCookie(
                response,
                REFRESH_TOKEN,
                newRefresh,
                (int) (refreshRemaining) / 1000,
                false
        );

        return ResponseDto.success(
                "토큰 재발급 완료",
                LoginResponseDto.of(newAccess, accessExpiresIn)
        );
    }

    @Override
    @Transactional
    public ResponseDto<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.getCookie(request, REFRESH_TOKEN).ifPresent(cookie -> {
            String refreshToken = cookie.getValue();

            if (jwtProvider.isValidToken(refreshToken)) {
                String username = jwtProvider.getUsernameFromJwt(refreshToken);
                userRepository.findByLoginId(username).ifPresent(user -> refreshTokenRepository.deleteByUser(user));
            }
        });

        CookieUtils.deleteCookie(response, REFRESH_TOKEN);

        return ResponseDto.success("로그아웃 완료");
    }
}
