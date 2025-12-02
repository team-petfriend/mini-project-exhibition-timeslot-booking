package org.example.exhibitiontimeslotbooking.service.review;

import org.example.exhibitiontimeslotbooking.dto.review.request.ReviewCreateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.review.request.ReviewUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.review.response.ReviewResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {
    Page<ReviewResponseDto> getReviews(Long exhibitionId, Integer rating, Pageable pageable);

    ReviewResponseDto createReviewWithFiles(Long exhibitionId, Long id, ReviewCreateRequestDto request, List<MultipartFile> files);

    ReviewResponseDto updateReview(Long reviewId, Long userId, ReviewUpdateRequestDto request, boolean isAdmin);

    void deleteReview(Long reviewId, Long id, boolean idAdmin);
}
