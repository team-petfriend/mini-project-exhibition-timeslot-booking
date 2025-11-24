package org.example.exhibitiontimeslotbooking.dto.exbitions_file.response;

import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;

public record ExhibitionFileResponseDto(
        Long fileId,
        String originalName,
        String storedName,
        String contentType,
        Long fileSize
) {
    public static ExhibitionFileResponseDto from(
            FileInfo fileInfo
    ) {
        if (fileInfo == null) return null;

        return new ExhibitionFileResponseDto(
                fileInfo.getId(),
                fileInfo.getOriginalName(),
                fileInfo.getStoredName(),
                fileInfo.getContentType(),
                fileInfo.getFileSize()
        );
    }
}
