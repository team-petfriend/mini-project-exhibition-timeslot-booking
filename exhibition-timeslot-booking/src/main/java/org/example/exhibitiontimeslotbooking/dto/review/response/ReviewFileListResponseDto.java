package org.example.exhibitiontimeslotbooking.dto.review.response;

import lombok.Builder;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;

@Builder
public record ReviewFileListResponseDto(
        Long fileId,
        String originalName,
        String storedName,
        String contentType,
        Long fileSize,
        String downloadUrl
) {
    public static ReviewFileListResponseDto fromEntity(FileInfo fileInfo, String baseDownLoadUrl) {
        if (fileInfo == null) return null;

        return ReviewFileListResponseDto.builder()
                .fileId(fileInfo.getId())
                .originalName(fileInfo.getOriginalName())
                .storedName(fileInfo.getStoredName())
                .contentType(fileInfo.getContentType())
                .fileSize(fileInfo.getFileSize())
                .downloadUrl(baseDownLoadUrl + fileInfo.getId())
                .build();
    }
}
