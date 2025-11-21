package org.example.exhibitiontimeslotbooking.repository.review;

import org.example.exhibitiontimeslotbooking.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
