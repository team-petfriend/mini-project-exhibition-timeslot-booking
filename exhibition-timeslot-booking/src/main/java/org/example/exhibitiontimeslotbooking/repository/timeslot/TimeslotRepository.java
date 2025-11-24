package org.example.exhibitiontimeslotbooking.repository.timeslot;

import org.example.exhibitiontimeslotbooking.entity.timeslot.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeslotRepository extends JpaRepository<Timeslot, Long> {
}
