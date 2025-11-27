package org.example.exhibitiontimeslotbooking.service.booking.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.bookings.BookingStatus;
import org.example.exhibitiontimeslotbooking.common.enums.errors.ErrorCode;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.booking.request.BookingCreateRequest;
import org.example.exhibitiontimeslotbooking.dto.booking.request.BookingUpdateRequest;
import org.example.exhibitiontimeslotbooking.dto.booking.response.BookingDetailResponse;
import org.example.exhibitiontimeslotbooking.dto.booking.response.BookingListResponse;
import org.example.exhibitiontimeslotbooking.dto.booking.response.BookingResponse;
import org.example.exhibitiontimeslotbooking.entity.booking.Booking;
import org.example.exhibitiontimeslotbooking.entity.timeslot.Timeslot;
import org.example.exhibitiontimeslotbooking.entity.user.User;
import org.example.exhibitiontimeslotbooking.repository.booking.BookingRepository;
import org.example.exhibitiontimeslotbooking.repository.timeslot.TimeslotRepository;
import org.example.exhibitiontimeslotbooking.repository.user.UserRepository;
import org.example.exhibitiontimeslotbooking.security.user.UserPrincipal;
import org.example.exhibitiontimeslotbooking.service.booking.BookingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final TimeslotRepository timeslotRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public ResponseDto<BookingDetailResponse> createBooking(UserPrincipal principal, BookingCreateRequest request) {
        Timeslot timeslot = timeslotRepository.findById(request.timeslotId())
                .orElseThrow(() -> new EntityNotFoundException("해당 id의 timeslot을 찾을 수 없습니다."));

        User user = userRepository.findByLoginId(principal.getLoginId())
                .orElseThrow(() -> new EntityNotFoundException("유저를 찾을 수 없습니다."));

        Booking booking = bookingRepository.save(Booking.builder()
                .user(user)
                .timeslot(timeslot)
                .qty(request.qty())
                .amount(request.amount())
                .build());

        BookingDetailResponse data = BookingDetailResponse.from(booking);
        return ResponseDto.success("SUCCESS", data );
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseDto<BookingListResponse> getAllBooking(UserPrincipal principal) {
        User user = userRepository.findByLoginId(principal.getLoginId())
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다ㅣ."));
        List<Booking> bookings = bookingRepository.findByUser(user);

        List<BookingResponse> list = bookings.stream()
                .map(BookingResponse::from)
                .toList();
        return ResponseDto.success("SUCCESS", BookingListResponse.from(list));
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseDto<BookingDetailResponse> getBookingById(UserPrincipal principal, Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("해당 id의 예약을 찾을 수 없습니다."));

        if(!booking.getUser().getLoginId().equals(principal.getLoginId())){
            throw new IllegalArgumentException("권한 없음");
        }

        BookingDetailResponse data = BookingDetailResponse.from(booking);
        return ResponseDto.success("success", data);

    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseDto<BookingDetailResponse> cancelBooking(UserPrincipal principal, Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("해당 id의 예약 정보를 찾을 수 없습니다."));

        if(!booking.getUser().getLoginId().equals(principal.getLoginId())){
            throw new IllegalArgumentException("권한없음");
        }

        booking.setBookingStatus(BookingStatus.CANCELED);
        BookingDetailResponse data = BookingDetailResponse.from(booking);
        return ResponseDto.success("SUCCESS", data);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseDto<BookingDetailResponse> refundBooking(UserPrincipal principal, Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 예약 정보를 찾을 수 없습니다."));

        if (!booking.getUser().getLoginId().equals(principal.getLoginId())) {
            throw new SecurityException("본인의 예약만 환불할 수 있습니다.");
        }
        if (booking.getStatus() != BookingStatus.CANCELED) {
            throw new IllegalStateException("취소된 예약만 환불할 수 있습니다.");
        }

        booking.setBookingStatus(BookingStatus.REFUNDED);

        BookingDetailResponse data = BookingDetailResponse.from(booking);
        return ResponseDto.success("SUCCESS", data);
    }
}
