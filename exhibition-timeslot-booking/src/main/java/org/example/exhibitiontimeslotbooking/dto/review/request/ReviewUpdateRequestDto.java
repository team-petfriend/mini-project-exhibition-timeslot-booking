package org.example.exhibitiontimeslotbooking.dto.review.request;

public record ReviewUpdateRequestDto(
        Integer rating,
        String content
) {}
