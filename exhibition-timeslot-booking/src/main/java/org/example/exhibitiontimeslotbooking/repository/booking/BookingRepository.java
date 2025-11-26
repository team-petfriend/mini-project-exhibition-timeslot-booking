package org.example.exhibitiontimeslotbooking.repository.booking;

import org.example.exhibitiontimeslotbooking.entity.booking.Booking;
import org.example.exhibitiontimeslotbooking.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
}
