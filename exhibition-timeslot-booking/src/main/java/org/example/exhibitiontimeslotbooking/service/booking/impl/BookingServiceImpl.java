package org.example.exhibitiontimeslotbooking.service.booking.impl;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.repository.booking.BookingRepository;
import org.example.exhibitiontimeslotbooking.service.booking.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
}
