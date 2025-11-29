package org.example.exhibitiontimeslotbooking.common.enums.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    SUCCESS(HttpStatus.OK, "S000", "성공적으로 완료되었습니다.", "success"),
    CREATED(HttpStatus.CREATED, "S001", "데이터 생성이 성공적으로 완료되었습니다.", "created successfully"),

    // ===========================
    // Common Errors (Cxxx)
    // ===========================
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "C001", "잘못된 입력값입니다.", "Invalid input parameter"),
    INVALID_TYPE(HttpStatus.BAD_REQUEST, "C002", "잘못된 요청 타입입니다.", "Type mismatch"),
    INVALID_JSON(HttpStatus.BAD_REQUEST, "C003", "JSON 형식이 올바르지 않습니다.", "JSON parse error"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C004", "지원하지 않는 HTTP 요청 방식입니다.", "HTTP method not supported"),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "C005", "데이터를 찾을 수 없습니다.", "Entity not found"),
    DB_CONSTRAINT(HttpStatus.CONFLICT, "C006", "데이터 제약 조건 위반입니다.", "Database constraint violation"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C007", "서버 내부 오류가 발생했습니다.", "Internal server error"),

    // ===========================
    // Authentication / Token (Axxx)
    // ===========================
    INVALID_AUTH(HttpStatus.UNAUTHORIZED, "A001", "인증 정보가 올바르지 않습니다.", "Invalid credentials"),
    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "A002", "인증에 실패했습니다.", "Authentication failed"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "A003", "접근 권한이 없습니다.", "Access denied"),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A004", "토큰이 만료되었습니다.", "Token expired"),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "A005", "유효하지 않은 토큰입니다.", "Invalid token"),
    PASSWORD_CONFIRM_MISMATCH(HttpStatus.BAD_REQUEST, "A006", "비밀번호와 비밀번호 확인이 일치하지 않습니다.", "Password mismatch"),

    // ===========================
    // User (Uxxx)
    // ===========================
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "사용자를 찾을 수 없습니다.", "User not found"),
    DUPLICATE_USER(HttpStatus.CONFLICT, "U002", "이미 존재하는 사용자입니다.", "Duplicate user"),

    // ===========================
    // Venue (Vxxx)
    // ===========================
    VENUE_NOT_FOUND(HttpStatus.NOT_FOUND, "V001", "전시장을 찾을 수 없습니다.", "Venue not fount"),
    DUPLICATE_VENUE(HttpStatus.CONFLICT, "V002", "이미 존재하는 전시장입니다.", "Duplicate venue"),

    // ===========================
    // Exhibition (Bxxx)
    // ===========================
    EXHIBITION_NOT_FOUND(HttpStatus.NOT_FOUND, "B001", "전시회를 찾을 수 없습니다.", "Exhibition not fount"),
    EXHIBITION_CANCEL_ONLY_SCHEDULED(HttpStatus.BAD_REQUEST, "B002", "상태가 SCHEDULED인 상태만 취소할 수 있습니다.", "Exhibition bad request"),
    EXHIBITION_ALREADY_CANCELED(HttpStatus.CONFLICT, "B003", "이미 취소된 전시회입니다.", "Exhibition conflict"),
    EXHIBITION_STATUS_FINALIZED(HttpStatus.BAD_REQUEST, "B004", "이미 종료된 전시회입니다.", "Exhibition bad request"),
    EXHIBITION_NOT_STARTED(HttpStatus.BAD_REQUEST, "B005", "시작전 입니다.", "Exhibition Not Open"),
    EXHIBITION_IS_CLOSED(HttpStatus.BAD_REQUEST, "B006", "종료되었습니다.", "Exhibition Is Closed"),

    // ===========================
    // Timeslot (Txxx)
    // ===========================
    TIMESLOT_NOT_FOUND(HttpStatus.NOT_FOUND, "T001", "타임슬롯을 찾을 수 없습니다.", "Timeslot not found"),
    TIMESLOT_ALREADY_EXISTS(HttpStatus.CONFLICT, "T002", "타임슬롯이 이미 존재합니다.", "Duplicate timeslot"),
    TIMESLOT_ALREADY_CANCELED(HttpStatus.CONFLICT, "T003", "이미 취소된 타임슬롯입니다.", "Timeslot is Canceled"),
    TIMESLOT_OUT_OF_EXHIBITION_RANGE(HttpStatus.BAD_REQUEST, "T004", "타임슬롯 시간은 전시회 시작~종료 시간 안에 있어야 합니다.", "slot time exceeds limit"),
    TIMESLOT_CHECK_CAPACITY(HttpStatus.BAD_REQUEST, "T005", "해당 파트타임의 예약 인원이 다찼습니다.", "Check Capacity"),

    // ===========================
    // File (Fxxx)
    // ===========================
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "F001", "파일을 찾을 수 없습니다.", "File not found"),
    FILE_CHECK_MAX(HttpStatus.BAD_REQUEST, "F002", "파일 갯수의 최대값은 4개입니다.", "Check File MAX_ATTACH");

    private final HttpStatus status;
    private final String code;
    private final String message;     // client-friendly
    private final String logMessage;  // developer-friendly

    ErrorCode(HttpStatus status, String code, String message, String logMessage) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.logMessage = logMessage;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (HTTP %d)", code, logMessage, status.value());
    }
}
