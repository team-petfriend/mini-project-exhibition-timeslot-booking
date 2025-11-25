package org.example.exhibitiontimeslotbooking.exception;

import lombok.Getter;
import org.example.exhibitiontimeslotbooking.common.enums.errors.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String detailMessage; // 추가 메시지(Optional)

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detailMessage = null;
    }

    public BusinessException(ErrorCode errorCode, String detailMessage) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detailMessage = detailMessage;
    }

    public HttpStatus getStatus() {
        return errorCode.getStatus();
    }

    @Override
    public String toString() {
        return "[BusinessException] " + errorCode +
                (detailMessage != null ? " / detail: " + detailMessage : "");
    }
}
