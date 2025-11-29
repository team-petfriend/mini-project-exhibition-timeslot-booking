package org.example.exhibitiontimeslotbooking.service.exhibition;

import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.exbitions_file.request.ExhibitionsFileUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.exbitions_file.response.ExhibitionFileResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExhibitionFileService {
    ResponseDto<Void> uploadExhibitionFiles(Long exhibitionId, List<MultipartFile> files);

    ResponseDto<Void> deleteExhibitionFile(Long exhibitionId, Long fileId);

    ResponseDto<Void> updateExhibitionFiles(Long exhibitionId, ExhibitionsFileUpdateRequestDto request);

    ResponseDto<List<ExhibitionFileResponseDto>> getByIdExhibitionFiles(Long exhibitionId);
}
