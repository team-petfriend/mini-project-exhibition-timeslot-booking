package org.example.exhibitiontimeslotbooking.dto.auth.response;

public record LoginResponseDto(
        String accessToken,
        String refreshToken,
        long accessTokenExpiresInMillis
) {
    public static LoginResponseDto of(String accessToken, String refreshToken, long accessTokenExpiresInMillis) {
        return new LoginResponseDto(accessToken, refreshToken, accessTokenExpiresInMillis);
    }
}
