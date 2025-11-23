package org.example.exhibitiontimeslotbooking.repository.booking;

import org.example.exhibitiontimeslotbooking.entity.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
