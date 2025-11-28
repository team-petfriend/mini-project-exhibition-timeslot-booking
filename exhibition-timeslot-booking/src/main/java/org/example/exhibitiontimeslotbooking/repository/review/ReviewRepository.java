package org.example.exhibitiontimeslotbooking.repository.review;

import org.example.exhibitiontimeslotbooking.entity.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByExhibitionId(Long exhibitionId, Pageable pageable);

    Page<Review> findByExhibitionIdAndRating(Long exhibitionId, Integer rating, Pageable pageable);

    boolean existsByExhibitionIdAndUserId(Long exhibitionId, Long userId);

    Optional<Review> findByExhibitionIdAndUserId(Long exhibitionId, Long userId);
}
