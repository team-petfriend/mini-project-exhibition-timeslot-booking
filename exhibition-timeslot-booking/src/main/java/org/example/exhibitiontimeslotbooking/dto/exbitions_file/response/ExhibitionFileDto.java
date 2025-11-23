package org.example.exhibitiontimeslotbooking.dto.exbitions_file.response;

import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;

public record ExhibitionFileDto(
        Long fileId,
        String originalName,
        String storedName,
        String contentType,
        Long fileSize
) {
    public static ExhibitionFileDto from(
            FileInfo fileInfo
    ) {
        if (fileInfo == null) return null;

        return new ExhibitionFileDto(
                fileInfo.getId(),
                fileInfo.getOriginalName(),
                fileInfo.getStoredName(),
                fileInfo.getContentType(),
                fileInfo.getFileSize()
        );
    }
}
