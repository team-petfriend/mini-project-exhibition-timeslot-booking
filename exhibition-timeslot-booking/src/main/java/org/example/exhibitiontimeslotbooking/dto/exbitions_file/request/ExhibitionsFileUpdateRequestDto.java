package org.example.exhibitiontimeslotbooking.dto.exbitions_file.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ExhibitionsFileUpdateRequestDto(
        Long exhibitionId,
        List<Long> fileIds,
        List<MultipartFile> newFiles
) {
}
