package org.example.exhibitiontimeslotbooking.service.booking;

import jakarta.validation.Valid;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.booking.request.BookingCreateRequest;
import org.example.exhibitiontimeslotbooking.dto.booking.request.BookingUpdateRequest;
import org.example.exhibitiontimeslotbooking.dto.booking.response.BookingDetailResponse;
import org.example.exhibitiontimeslotbooking.dto.booking.response.BookingListResponse;
import org.example.exhibitiontimeslotbooking.security.user.UserPrincipal;

public interface BookingService {
    ResponseDto<BookingDetailResponse> createBooking(@Valid BookingCreateRequest request);

    ResponseDto<BookingListResponse> getAllBooking(UserPrincipal userPrincipal);

    ResponseDto<BookingDetailResponse> getBookingById(Long bookingId);

    ResponseDto<BookingDetailResponse> cancelBooking(Long bookingId ,@Valid BookingUpdateRequest request);

    ResponseDto<BookingDetailResponse> refundBooking(Long bookingId, @Valid BookingUpdateRequest request);
}
