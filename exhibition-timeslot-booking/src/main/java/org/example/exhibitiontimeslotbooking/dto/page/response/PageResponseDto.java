package org.example.exhibitiontimeslotbooking.dto.page.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponseDto<T> {
    private final List<T> content;
    private final int currentPage;
    private final int totalPages;
    private final long totalElements;

    @Builder
    public PageResponseDto(List<T> content, int currentPage, int totalPages, long totalElements) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}
