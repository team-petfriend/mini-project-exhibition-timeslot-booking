package org.example.exhibitiontimeslotbooking.controller.exhibition;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.exbitions_file.request.ExhibitionsFileUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.exbitions_file.response.ExhibitionFileResponseDto;
import org.example.exhibitiontimeslotbooking.service.exhibition.ExhibitionFileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.Exhibitions.EXHIBITION_FILE)
@RequiredArgsConstructor
public class ExhibitionFileController {
    private final ExhibitionFileService exhibitionFileservice;

    @PostMapping
    public ResponseEntity<ResponseDto<Void>> uploadExhibitionFiles(
            @PathVariable Long exhibitionId,
            @RequestParam("files") List<MultipartFile> files
            ) {
        ResponseDto<Void> data = exhibitionFileservice.uploadExhibitionFiles(exhibitionId, files);

        return ResponseEntity.ok(data);
    }

    @GetMapping(ApiMappingPattern.Exhibitions.EXHIBITION_FILE_ID)
    public ResponseEntity<ResponseDto<List<ExhibitionFileResponseDto>>> getByIdExhibitionFiles(
            @PathVariable Long exhibitionId
    ) {
        ResponseDto<List<ExhibitionFileResponseDto>> data = exhibitionFileservice.getByIdExhibitionFiles(exhibitionId);

        return ResponseEntity.ok(data);
    }

    @DeleteMapping(ApiMappingPattern.Exhibitions.EXHIBITION_FILE_ID)
    public ResponseEntity<ResponseDto<Void>> deleteExhibitionFile(
            @PathVariable Long exhibitionId,
            @PathVariable Long fileId
    ) {

        ResponseDto<Void> data = exhibitionFileservice.deleteExhibitionFile(exhibitionId, fileId);

        return ResponseEntity.ok(data);
    }

    @PutMapping(value = ApiMappingPattern.Exhibitions.EXHIBITION_FILE_ID, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateExhibitionFiles(
            @PathVariable Long exhibitionId,
            @ModelAttribute ExhibitionsFileUpdateRequestDto request
            ) {
        ResponseDto<Void> data = exhibitionFileservice.updateExhibitionFiles(exhibitionId, request);
        return ResponseEntity.ok(data);
    }
}
