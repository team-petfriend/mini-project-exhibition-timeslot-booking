package org.example.exhibitiontimeslotbooking.dto.review.response;

import org.example.exhibitiontimeslotbooking.entity.review.Review;

public record ReviewListResponseDto(
        Long id,
        Integer rating,
        String content
) {
    public static ReviewListResponseDto fromEntity(Review review) {
        return new ReviewListResponseDto(
                review.getId(),
                review.getRating(),
                review.getContent()
        );
    }
}
