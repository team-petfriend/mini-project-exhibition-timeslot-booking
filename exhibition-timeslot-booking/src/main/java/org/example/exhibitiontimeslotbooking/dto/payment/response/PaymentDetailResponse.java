//package org.example.exhibitiontimeslotbooking.dto.payment.response;
//
//import org.example.exhibitiontimeslotbooking.common.enums.payment.PaymentStatus;
//import org.example.exhibitiontimeslotbooking.entity.payment.Payment;
//
//import java.time.LocalDateTime;
//
//public record PaymentDetailResponse(
//        Long id,
//        Long bookingId,
//        Integer amount,
//        String currency,
//        String method,
//        PaymentStatus paymentStatus,
//        LocalDateTime paidAt
//) {
//    public static PaymentDetailResponse from(Payment payment){
//        return new PaymentDetailResponse(
//                payment.getId(),
//                payment.getBookingId().getId(),
//                payment.getAmount(),
//                payment.getCurrency(),
//                payment.getMethod(),
//                payment.getPaymentStatus(),
//                payment.getPaidAt()
//        );
//    }
//}
