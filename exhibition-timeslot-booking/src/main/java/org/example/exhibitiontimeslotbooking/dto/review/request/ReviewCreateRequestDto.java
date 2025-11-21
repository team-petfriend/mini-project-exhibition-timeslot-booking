package org.example.exhibitiontimeslotbooking.dto.review.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.entity.review.Review;
import org.example.exhibitiontimeslotbooking.entity.user.User;

public record ReviewCreateRequestDto(
        Long exhibitionId,

        @Min(1) @Max(5)
        Integer rating,

        String content,

        Long reviewFileId
) {
        public Review toEntity(User user, Exhibition exhibition, FileInfo reviewFile) {
                return Review.builder()
                        .user(user)
                        .exhibition(exhibition)
                        .rating(rating)
                        .content(content)
                        .reviewFile(reviewFile)
                        .build();
        }
}
