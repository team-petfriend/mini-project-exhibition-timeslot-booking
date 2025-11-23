package org.example.exhibitiontimeslotbooking.dto.venues.response;

import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;

public record VenueFileResponse(
        Long id,
        String url,
        String originalName,
        Long fileSize
) {
    public static VenueFileResponse from(FileInfo fileInfo) {

        if (fileInfo == null) return null;

        return new VenueFileResponse(
                fileInfo.getId(),
                fileInfo.getFilePath(),
                fileInfo.getOriginalName(),
                fileInfo.getFileSize()
        );
    }

}
