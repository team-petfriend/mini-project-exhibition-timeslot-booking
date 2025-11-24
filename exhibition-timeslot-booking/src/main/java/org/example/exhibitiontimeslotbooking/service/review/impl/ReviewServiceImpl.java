package org.example.exhibitiontimeslotbooking.service.review.impl;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.repository.review.ReviewRepository;
import org.example.exhibitiontimeslotbooking.service.review.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
}
