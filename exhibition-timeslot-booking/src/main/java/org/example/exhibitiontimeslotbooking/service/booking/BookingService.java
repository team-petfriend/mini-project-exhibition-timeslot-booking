package org.example.exhibitiontimeslotbooking.service.booking;

import jakarta.validation.Valid;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.booking.request.BookingCreateRequest;
import org.example.exhibitiontimeslotbooking.dto.booking.request.BookingUpdateRequest;
import org.example.exhibitiontimeslotbooking.dto.booking.response.BookingDetailResponse;
import org.example.exhibitiontimeslotbooking.dto.booking.response.BookingListResponse;
import org.example.exhibitiontimeslotbooking.security.user.UserPrincipal;

public interface BookingService {

    ResponseDto<BookingDetailResponse> createBooking(UserPrincipal principal, @Valid BookingCreateRequest request);

    ResponseDto<BookingListResponse> getAllBooking(UserPrincipal principal);

    ResponseDto<BookingDetailResponse> getBookingById(UserPrincipal principal, Long bookingId);

    ResponseDto<BookingDetailResponse> cancelBooking(UserPrincipal principal, Long bookingId);

    ResponseDto<BookingDetailResponse> refundBooking(UserPrincipal principal, Long bookingId);
}
