package org.example.exhibitiontimeslotbooking.dto.review.request;

public record ReviewCreateRequestDto(
        Integer rating,
        String content
) {}
