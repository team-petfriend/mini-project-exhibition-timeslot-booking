package org.example.exhibitiontimeslotbooking.repository.exhibition;

import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ExhibitionRepository extends JpaRepository<Exhibition, Long> {

    @Query("""
       SELECT e FROM Exhibition e
       WHERE e.venue.id = :venueId
         AND (e.title LIKE :keyword OR e.description LIKE :keyword)
       """)
    Page<Exhibition> searchExhibitionByKeyword(@Param("venueId") Long venueId, @Param("keyword") String searchKeyword, Pageable pageable);
}
