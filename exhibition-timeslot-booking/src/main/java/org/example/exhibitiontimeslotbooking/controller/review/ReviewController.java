package org.example.exhibitiontimeslotbooking.controller.review;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.review.request.ReviewCreateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.review.request.ReviewUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.review.response.ReviewResponseDto;
import org.example.exhibitiontimeslotbooking.security.user.UserPrincipal;
import org.example.exhibitiontimeslotbooking.service.review.ReviewFileService;
import org.example.exhibitiontimeslotbooking.service.review.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewFileService reviewFileService;

    @GetMapping(ApiMappingPattern.Reviews.ROOT)
    public ResponseDto<Page<ReviewResponseDto>> getReviews(
            @PathVariable Long exhibitionId,
            @RequestParam(required = false) Integer rating,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<ReviewResponseDto> reviews = reviewService.getReviews(exhibitionId, rating, pageable);
        return ResponseDto.success("리뷰 목록 조회",reviews);
    }

    @PostMapping(ApiMappingPattern.Reviews.ROOT)
    public ResponseDto<ReviewResponseDto> createReview(
            @PathVariable Long exhibitionId,
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestBody ReviewCreateRequestDto request
    ) {
        ReviewResponseDto responseDto = reviewService.createReview(exhibitionId, principal.getId(), request);
        return ResponseDto.success("리뷰 작성 완료", responseDto);
    }

    @PostMapping(ApiMappingPattern.Reviews.REVIEW_FILES)
    public ResponseDto<Void> uploadReviewFiles(
            @PathVariable Long exhibitionId,
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestParam("files") List<MultipartFile> files
    ) {
        reviewFileService.uploadReviewFiles(exhibitionId, principal.getId(), files);
        return ResponseDto.success("리뷰 사진 업로드 완료");
    }

    @PutMapping(ApiMappingPattern.Reviews.BY_ID)
    public ResponseDto<ReviewResponseDto> updateReview(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestBody ReviewUpdateRequestDto request
    ) {
        ReviewResponseDto response = reviewService.updateReview(reviewId, principal.getId(), request);
        return ResponseDto.success("리뷰 수정 완료", response);
    }

    @DeleteMapping(ApiMappingPattern.Reviews.BY_ID)
    public ResponseDto<Void> deleteReview(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        reviewService.deleteReview(reviewId, principal.getId(), principal.isAdmin());
        return ResponseDto.success("리뷰 삭제 완료");
    }
}
