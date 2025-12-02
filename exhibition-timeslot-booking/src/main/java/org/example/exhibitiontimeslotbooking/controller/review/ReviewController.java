package org.example.exhibitiontimeslotbooking.controller.review;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.example.exhibitiontimeslotbooking.common.enums.RoleType;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.review.request.ReviewCreateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.review.request.ReviewUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.review.response.ReviewResponseDto;
import org.example.exhibitiontimeslotbooking.security.user.UserPrincipal;
import org.example.exhibitiontimeslotbooking.service.review.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping(value = ApiMappingPattern.Reviews.ROOT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto<ReviewResponseDto> createReviewWithFiles(
            @PathVariable Long exhibitionId,
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestParam("review") String reviewJson,
            @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) {
        ReviewCreateRequestDto request;
        try {
            ObjectMapper mapper = new ObjectMapper();
            request = mapper.readValue(reviewJson, ReviewCreateRequestDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("JSON 형식이 올바르지 않습니다: " + reviewJson, e);
        }

        ReviewResponseDto responseDto = reviewService.createReviewWithFiles(exhibitionId, principal.getId(), request, files);
        return ResponseDto.success("리뷰 작성 완료", responseDto);
    }

    @GetMapping(ApiMappingPattern.Reviews.ROOT)
    public ResponseDto<Page<ReviewResponseDto>> getReviews(
            @PathVariable Long exhibitionId,
            @RequestParam(required = false) Integer rating,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<ReviewResponseDto> reviews = reviewService.getReviews(exhibitionId, rating, pageable);
        return ResponseDto.success("리뷰 목록 조회",reviews);
    }

    @PutMapping(ApiMappingPattern.Reviews.BY_ID)
    public ResponseDto<ReviewResponseDto> updateReview(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestBody ReviewUpdateRequestDto request
    ) {
        boolean isAdmin = principal.hasRole(RoleType.ADMIN);
        ReviewResponseDto response = reviewService.updateReview(reviewId, principal.getId(), request, isAdmin);
        return ResponseDto.success("리뷰 수정 완료", response);
    }

    @DeleteMapping(ApiMappingPattern.Reviews.BY_ID)
    public ResponseDto<Void> deleteReview(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        reviewService.deleteReview(reviewId, principal.getId(), principal.hasRole(RoleType.ADMIN));
        return ResponseDto.success("리뷰 삭제 완료");
    }
}
