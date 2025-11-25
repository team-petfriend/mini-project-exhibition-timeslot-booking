package org.example.exhibitiontimeslotbooking.security.provider;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Component
public class JwtProvider {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String CLAIM_ROLES = "roles";
    public static final String CLAIM_EMAIL = "email";
    public static final String CLAIM_TYPE = "type";

    private final SecretKey key;
    private final long accessExpMs;
    private final long refreshExpMs;
    private final long emailExpMs;
    private final int clockSkewSeconds;

    private final JwtParser parser;

    public JwtProvider(
            @Value("${jwt.secret}") String base64Secret,
            @Value("${jwt.expiration}") long accessExpMs,
            @Value("${jwt.refresh-expiration}") long refreshExpMs,
            @Value("${jwt.email-expiration}") long emailExpMs,
            @Value("${jwt.clock-skew-seconds:0}") int clockSkewSeconds
    ) {
        byte[] secretBytes = Decoders.BASE64.decode(base64Secret);
        if (secretBytes.length < 32) {
            throw new IllegalArgumentException("jwt.secret must be at least 256 bits (32 bytes) when Base64-decoded.");
        }
        this.key = Keys.hmacShaKeyFor(secretBytes);

        this.accessExpMs = accessExpMs;
        this.refreshExpMs = refreshExpMs;
        this.emailExpMs = emailExpMs;
        this.clockSkewSeconds = Math.max(clockSkewSeconds, 0);

        this.parser = Jwts.parser()
                .setSigningKey(this.key)
                .setAllowedClockSkewSeconds(this.clockSkewSeconds)
                .build();
    }

    public String generateAccessToken(String username, Set<String> roles) {
        return buildToken(username, roles, accessExpMs);
    }

    public String generateRefreshToken(String username, Set<String> roles) {
        return buildToken(username, roles, refreshExpMs);
    }

    public String generateEmailJwtToken(String email, String type) {
        long now = System.currentTimeMillis();
        Date iat = new Date(now);
        Date exp = new Date(now + emailExpMs);

        return Jwts.builder()
                .setSubject(email)
                .claim(CLAIM_EMAIL, email)
                .claim(CLAIM_TYPE, type)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public boolean isValidEmailToken(String token, String expectedType) {
        try {
            Claims claims = parseClaimsJws(token);
            String type = claims.get(CLAIM_TYPE, String.class);
            return expectedType == null ? type != null : expectedType.equals(type);
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return parseClaimsJws(token);
    }

    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    public String getUsernameFromJwt(String token) {
        return getSubject(token);
    }

    public String getEmailFromEmailToken(String token) {
        Claims c = getClaims(token);
        String email = c.get(CLAIM_EMAIL, String.class);
        return (email != null) ? email : c.getSubject();
    }

    @SuppressWarnings("unchecked")
    public Set<String> getRolesFromJwt(String token) {
        Claims c = getClaims(token);
        Object raw = c.get(CLAIM_ROLES);
        if (raw == null) return Set.of();

        if (raw instanceof Collection<?> coll) {
            Set<String> result = new HashSet<>();
            for (Object o : coll) if (o != null) result.add(o.toString());
            return result;
        }
        return Set.of(raw.toString());
    }

    public long getRemainingMillis(String token) {
        Claims c = getClaims(token);
        Date exp = c.getExpiration();
        return (exp == null) ? -1L : (exp.getTime() - System.currentTimeMillis());
    }

    public String removeBearer(String bearerToken) {
        if (bearerToken == null || !bearerToken.startsWith(BEARER_PREFIX)) {
            throw new IllegalArgumentException("Authorization 형식이 유효하지 않습니다.");
        }
        return bearerToken.substring(BEARER_PREFIX.length()).trim();
    }

    private Claims parseClaimsJws(String token) {
        JwtParser p = this.parser;
        // parser.parseClaimsJws throws various JwtException (ExpiredJwtException, MalformedJwtException, etc.)
        Jws<Claims> jws = p.parseClaimsJws(token);
        return jws.getBody();
    }

    private String buildToken(String username, Set<String> roles, long expMs) {
        long now = System.currentTimeMillis();
        Date iat = new Date(now);
        Date exp = new Date(now + expMs);

        List<String> roleList = (roles == null) ? List.of() : new ArrayList<>(roles);

        return Jwts.builder()
                .setSubject(username)
                .claim(CLAIM_ROLES, roleList)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
