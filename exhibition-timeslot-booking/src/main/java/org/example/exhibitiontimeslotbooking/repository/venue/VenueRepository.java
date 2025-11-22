package org.example.exhibitiontimeslotbooking.repository.venue;

import org.example.exhibitiontimeslotbooking.entity.venue.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long> {
}
