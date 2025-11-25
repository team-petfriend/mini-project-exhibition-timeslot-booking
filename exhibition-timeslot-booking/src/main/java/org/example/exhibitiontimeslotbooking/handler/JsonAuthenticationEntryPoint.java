package org.example.exhibitiontimeslotbooking.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.exhibitiontimeslotbooking.common.enums.errors.ErrorCode;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {
        log.warn("[AuthenticationEntryPoint] 인증실패: {}", authException.getMessage());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");

        ResponseDto<?> body = ResponseDto.failure(
                "인증이 필요합니다. (로그인이 필요 혹은 토큰 만료)",
                HttpStatus.UNAUTHORIZED.value(),
                ErrorCode.INVALID_AUTH.name()
        );

        response.getWriter().write(objectMapper.writeValueAsString(body));

    }
}
