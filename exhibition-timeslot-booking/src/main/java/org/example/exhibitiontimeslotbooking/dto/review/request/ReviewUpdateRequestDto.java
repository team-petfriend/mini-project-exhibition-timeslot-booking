package org.example.exhibitiontimeslotbooking.dto.review.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record ReviewUpdateRequestDto(
        @Min(1) @Max(5)
        Integer rating,
        String content
) {}
