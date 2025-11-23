package org.example.exhibitiontimeslotbooking.service.impl.booking;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.repository.booking.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class BookingServiceImpl {
    private final BookingRepository bookingRepository;
}
