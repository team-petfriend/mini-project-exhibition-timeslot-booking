package org.example.exhibitiontimeslotbooking.repository.exhibition;

import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;
import org.example.exhibitiontimeslotbooking.entity.venue.Venue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {
    Page<Exhibition> searchExhibitionByKeyword(Long id, String keyword, Pageable pageable);
}
