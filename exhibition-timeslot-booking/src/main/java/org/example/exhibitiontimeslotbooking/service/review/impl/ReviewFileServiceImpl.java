package org.example.exhibitiontimeslotbooking.service.review.impl;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.entity.file.ReviewFile;
import org.example.exhibitiontimeslotbooking.entity.review.Review;
import org.example.exhibitiontimeslotbooking.repository.file.FileInfoRepository;
import org.example.exhibitiontimeslotbooking.repository.review.ReviewRepository;
import org.example.exhibitiontimeslotbooking.service.file.FileServiceImpl;
import org.example.exhibitiontimeslotbooking.service.review.ReviewFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewFileServiceImpl implements ReviewFileService {

    private final ReviewRepository reviewRepository;
    private final FileServiceImpl fileService;

    @Override
    @Transactional
    public void uploadReviewFiles(Long exhibitionId, Long userId, List<MultipartFile> files) {

        Review review = reviewRepository.findByExhibitionIdAndUserId(exhibitionId, userId)
                .orElseThrow(() -> new RuntimeException("리뷰가 존재하지 않습니다."));

        if (files == null || files.isEmpty()) {
            throw new RuntimeException("업로드할 파일이 없습니다.");
        }

        int order = 0;
        for (MultipartFile file : files) {

            FileInfo fileInfo = fileService.saveReviewImg(file);

            ReviewFile reviewFile = ReviewFile.builder()
                    .review(review)
                    .fileInfo(fileInfo)
                    .displayOrder(order++)
                    .build();

            review.addReviewFile(reviewFile);
            reviewRepository.save(review);
        }
    }
}
