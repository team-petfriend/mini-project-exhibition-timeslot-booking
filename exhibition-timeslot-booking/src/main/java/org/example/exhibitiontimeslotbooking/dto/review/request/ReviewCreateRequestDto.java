package org.example.exhibitiontimeslotbooking.dto.review.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCreateRequestDto {
        @Min(1) @Max(5)
        private Integer rating;

        private String content;
}
