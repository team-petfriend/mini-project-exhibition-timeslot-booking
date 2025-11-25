package org.example.exhibitiontimeslotbooking.security.oauth2.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.exhibitiontimeslotbooking.common.enums.errors.ErrorCode;
import org.example.exhibitiontimeslotbooking.entity.auth.RefreshToken;
import org.example.exhibitiontimeslotbooking.entity.user.User;
import org.example.exhibitiontimeslotbooking.exception.BusinessException;
import org.example.exhibitiontimeslotbooking.repository.auth.RefreshTokenRepository;
import org.example.exhibitiontimeslotbooking.repository.user.UserRepository;
import org.example.exhibitiontimeslotbooking.security.provider.JwtProvider;
import org.example.exhibitiontimeslotbooking.security.user.UserPrincipal;
import org.example.exhibitiontimeslotbooking.security.util.CookieUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${app.oauth2.authorized-redirect-uri}")
    private String redirectUri;

    private static final String REFRESH_TOKEN = "refreshToken";

    @Override
    public void onAuthenticationSuccess (
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        String userId = principal.getLoginId();

        User user = userRepository.findByLoginId(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        Set<String> roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        String accessToken = jwtProvider.generateAccessToken(userId, roles);
        String refreshToken = jwtProvider.generateRefreshToken(userId, roles);

        long refreshMillis = jwtProvider.getRemainingMillis(refreshToken);

        Instant refreshExpiry = Instant.now().plusMillis(refreshMillis);

        refreshTokenRepository.findByUser(user)
                .ifPresentOrElse(
                        rt -> rt.renew(refreshToken, refreshExpiry),
                        () -> refreshTokenRepository.save(
                                RefreshToken.builder()
                                        .user(user)
                                        .token(refreshToken)
                                        .expiry(refreshExpiry)
                                        .build()
                        ));


        CookieUtils.addHttpOnlyCookie(
                response,
                REFRESH_TOKEN,
                refreshToken,
                (int) (refreshMillis / 1000),
                false
        );

        String targetUrl = UriComponentsBuilder.fromUriString(redirectUri)
                .queryParam("accessToken", accessToken)
                .build()
                .toUriString();

        clearAuthenticationAttributes(request);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
