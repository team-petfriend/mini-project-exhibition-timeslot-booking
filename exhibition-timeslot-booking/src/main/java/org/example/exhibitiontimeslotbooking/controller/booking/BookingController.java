package org.example.exhibitiontimeslotbooking.controller.booking;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.example.exhibitiontimeslotbooking.service.booking.BookingService;
import org.example.exhibitiontimeslotbooking.service.booking.impl.BookingServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiMappingPattern.Bookings.ROOT)
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
}
