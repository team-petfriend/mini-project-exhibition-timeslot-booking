package org.example.exhibitiontimeslotbooking.dto.review.response;

import org.example.exhibitiontimeslotbooking.entity.review.Review;

import java.time.LocalDateTime;

public record ReviewResponseDto(
        Long id,
        Long exhibitionId,
        Long userId,
        Integer rating,
        String content,
        Long reviewFileId,
        LocalDateTime createdAt
) {
    public static ReviewResponseDto from(Review review) {
        return new ReviewResponseDto(
                review.getId(),
                review.getExhibition().getId(),
                review.getUser().getId(),
                review.getRating(),
                review.getContent(),
                review.getReviewFile() != null ? review.getReviewFile().getId() : null,
                review.getCreatedAt()
        );
    }
}
