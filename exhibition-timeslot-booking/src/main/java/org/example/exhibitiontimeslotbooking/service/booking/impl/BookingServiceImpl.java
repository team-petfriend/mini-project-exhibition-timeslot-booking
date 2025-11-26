package org.example.exhibitiontimeslotbooking.service.booking.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.errors.ErrorCode;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.booking.request.BookingCreateRequest;
import org.example.exhibitiontimeslotbooking.dto.booking.request.BookingUpdateRequest;
import org.example.exhibitiontimeslotbooking.dto.booking.response.BookingDetailResponse;
import org.example.exhibitiontimeslotbooking.dto.booking.response.BookingListResponse;
import org.example.exhibitiontimeslotbooking.entity.booking.Booking;
import org.example.exhibitiontimeslotbooking.repository.booking.BookingRepository;
import org.example.exhibitiontimeslotbooking.repository.timeslot.TimeslotRepository;
import org.example.exhibitiontimeslotbooking.security.user.UserPrincipal;
import org.example.exhibitiontimeslotbooking.service.booking.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final TimeslotRepository timeslotRepository;

    @Override
    @Transactional
    public ResponseDto<BookingDetailResponse> createBooking(BookingCreateRequest request) {
        if(timeslotRepository.findById(request.timeslotId()).isEmpty()){
            throw new EntityNotFoundException("해당 Id의 Timeslot을 찾을 수 없습니다.");
        }
        return null;
    }

    @Override
    public ResponseDto<BookingListResponse> getAllBooking(UserPrincipal userPrincipal) {
        return null;
    }

    @Override
    public ResponseDto<BookingDetailResponse> getBookingById(Long bookingId) {
        return null;
    }

    @Override
    public ResponseDto<BookingDetailResponse> cancelBooking(Long bookingId, BookingUpdateRequest request) {
        return null;
    }

    @Override
    public ResponseDto<BookingDetailResponse> refundBooking(Long bookingId, BookingUpdateRequest request) {
        return null;
    }
}
