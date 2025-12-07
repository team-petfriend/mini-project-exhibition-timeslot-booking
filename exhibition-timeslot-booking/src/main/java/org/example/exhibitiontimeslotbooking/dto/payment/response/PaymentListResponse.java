//package org.example.exhibitiontimeslotbooking.dto.payment.response;
//
//import org.example.exhibitiontimeslotbooking.common.enums.payment.PaymentStatus;
//import org.example.exhibitiontimeslotbooking.entity.payment.Payment;
//
//import java.time.LocalDateTime;
//
//public record PaymentListResponse(
//        Long id,
//        Long bookingId,
//        String method,
//        PaymentStatus paymentStatus,
//        LocalDateTime createdAt,
//        LocalDateTime updatedAt
//) {
//    public static PaymentListResponse from(Payment payment){
//        return new PaymentListResponse(
//                payment.getId(),
//                payment.getBookingId().getId(),
//                payment.getMethod(),
//                payment.getPaymentStatus(),
//                payment.getCreatedAt(),
//                payment.getUpdatedAt()
//        );
//    }
//}
