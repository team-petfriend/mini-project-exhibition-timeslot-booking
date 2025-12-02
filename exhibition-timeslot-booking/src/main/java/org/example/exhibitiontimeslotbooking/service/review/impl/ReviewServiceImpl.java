package org.example.exhibitiontimeslotbooking.service.review.impl;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.dto.review.request.ReviewCreateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.review.request.ReviewUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.review.response.ReviewResponseDto;
import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.entity.file.ReviewFile;
import org.example.exhibitiontimeslotbooking.entity.review.Review;
import org.example.exhibitiontimeslotbooking.entity.user.User;
import org.example.exhibitiontimeslotbooking.repository.exhibition.ExhibitionRepository;
import org.example.exhibitiontimeslotbooking.repository.review.ReviewRepository;
import org.example.exhibitiontimeslotbooking.repository.user.UserRepository;
import org.example.exhibitiontimeslotbooking.service.file.FileServiceImpl;
import org.example.exhibitiontimeslotbooking.service.review.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final FileServiceImpl fileService;

    @Override
    public Page<ReviewResponseDto> getReviews(Long exhibitionId, Integer rating, Pageable pageable) {
        if (rating == null) {
            return reviewRepository.findByExhibitionIdAndRating(exhibitionId, rating, pageable)
                    .map(ReviewResponseDto::from);
        }
        return reviewRepository.findByExhibitionId(exhibitionId, pageable)
                .map(ReviewResponseDto::from);
    }

    @Override
    @Transactional
    public ReviewResponseDto createReviewWithFiles(Long exhibitionId, Long userId, ReviewCreateRequestDto request, List<MultipartFile> files) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new RuntimeException("전시장을 찾을 수 없습니다."));

        if (reviewRepository.existsByExhibitionIdAndUserId(exhibitionId, userId)) {
            throw new RuntimeException("이미 리뷰를 작성했습니다.");
        }

        Review review = Review.builder()
                .user(user)
                .exhibition(exhibition)
                .rating(request.getRating())
                .content(request.getContent())
                .build();

        review.validateRating();

        reviewRepository.save(review);

        if (files != null && !files.isEmpty()) {
            int order = 0;
            for (MultipartFile file : files) {
                FileInfo fileInfo = fileService.saveReviewImg(file);

                ReviewFile reviewFile = ReviewFile.builder()
                    .fileInfo(fileInfo)
                    .displayOrder(order++)
                    .build();

            review.addReviewFile(reviewFile);
            }
            reviewRepository.save(review);
        }

        return ReviewResponseDto.from(review);
    }

    @Override
    @Transactional
    public ReviewResponseDto updateReview(Long reviewId, Long userId, ReviewUpdateRequestDto request, boolean isAdmin) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰가 존재하지 않습니다."));

        if(!isAdmin && !review.getUser().getId().equals(userId)) {
            throw new RuntimeException("권한이 없습니다.");
        }

        if (request.rating() != null) {
            review.setRating(request.rating());
            review.validateRating();
        }
        if (request.content() != null) {
            review.setContent(request.content());
        }

        return ReviewResponseDto.from(review);
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId, Long userId, boolean idAdmin) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰가 존재하지 않습니다."));

        if (!idAdmin && !review.getUser().getId().equals(userId)) {
            throw new RuntimeException("권한이 없습니다.");
        }

        reviewRepository.delete(review);
    }
}
