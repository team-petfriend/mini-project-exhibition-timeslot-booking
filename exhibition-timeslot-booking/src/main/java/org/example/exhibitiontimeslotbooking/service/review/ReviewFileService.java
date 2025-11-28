package org.example.exhibitiontimeslotbooking.service.review;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewFileService {
    void uploadReviewFiles(Long exhibitionId, Long id, List<MultipartFile> files);
}
