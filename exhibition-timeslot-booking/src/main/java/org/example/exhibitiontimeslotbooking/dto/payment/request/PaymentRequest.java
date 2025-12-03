package org.example.exhibitiontimeslotbooking.dto.payment.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.exhibitiontimeslotbooking.common.enums.payment.PaymentMethod;

public record PaymentRequest(
        @NotBlank(message = "예약 id는 필수입니다.")
        String bookingId,

        @NotNull(message = "결제 금액은 필수입니다.")
        @Min(value = 100, message = "최소 결제 금액은 100원입니다.")
        Long amount,

        @NotNull(message = "결제 수단은 필수입니다.")
        PaymentMethod method
) {
}
