package org.example.exhibitiontimeslotbooking.dto.venues.response;

import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;

public record VenueFileResponseDto(
        Long id,
        String url,
        String originalName,
        Long fileSize
) {
    public static VenueFileResponseDto from(FileInfo fileInfo) {

        if (fileInfo == null) return null;

        return new VenueFileResponseDto(
                fileInfo.getId(),
                fileInfo.getFilePath(),
                fileInfo.getOriginalName(),
                fileInfo.getFileSize()
        );
    }

}
