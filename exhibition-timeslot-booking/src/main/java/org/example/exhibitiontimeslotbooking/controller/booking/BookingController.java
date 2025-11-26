package org.example.exhibitiontimeslotbooking.controller.booking;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.booking.request.BookingCreateRequest;
import org.example.exhibitiontimeslotbooking.dto.booking.request.BookingUpdateRequest;
import org.example.exhibitiontimeslotbooking.dto.booking.response.BookingDetailResponse;
import org.example.exhibitiontimeslotbooking.dto.booking.response.BookingListResponse;
import org.example.exhibitiontimeslotbooking.security.user.UserPrincipal;
import org.example.exhibitiontimeslotbooking.service.booking.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.Bookings.ROOT)
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<ResponseDto<BookingDetailResponse>> createBooking(
            @AuthenticationPrincipal UserPrincipal principal,
            @Valid @RequestBody BookingCreateRequest request
    ) {
        ResponseDto<BookingDetailResponse> response = bookingService.createBooking(principal, request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<BookingListResponse>> getAllBooking(
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        ResponseDto<BookingListResponse> response = bookingService.getAllBooking(principal);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(ApiMappingPattern.Bookings.BY_ID)
    public ResponseEntity<ResponseDto<BookingDetailResponse>> getBookingById(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable Long bookingId
    ) {
        ResponseDto<BookingDetailResponse> response = bookingService.getBookingById(principal, bookingId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(ApiMappingPattern.Bookings.BOOKING_CANCEL)
    public ResponseEntity<ResponseDto<BookingDetailResponse>> cancelBooking(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable Long bookingId
    ) {
        ResponseDto<BookingDetailResponse> response = bookingService.cancelBooking(principal, bookingId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(ApiMappingPattern.Bookings.BOOKING_REFUND)
    public ResponseEntity<ResponseDto<BookingDetailResponse>> refundBooking(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable Long bookingId
    ) {
        ResponseDto<BookingDetailResponse> response = bookingService.refundBooking(principal, bookingId);
        return ResponseEntity.ok().body(response);
    }


}
