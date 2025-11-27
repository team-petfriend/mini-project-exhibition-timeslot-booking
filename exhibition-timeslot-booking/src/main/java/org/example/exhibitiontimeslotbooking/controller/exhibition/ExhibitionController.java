package org.example.exhibitiontimeslotbooking.controller.exhibition;


import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.request.ExhibitionsCreateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.request.ExhibitionsStatusUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.request.ExhibitionsUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.response.ExhibitionDetailResponseDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.response.ExhibitionSummaryDto;
import org.example.exhibitiontimeslotbooking.service.exhibition.ExhibitionService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.Exhibitions.ROOT)
@RequiredArgsConstructor
public class ExhibitionController {

    private final ExhibitionService exhibitionService;

    // 전시회 생성
    @PostMapping
    public ResponseEntity<ResponseDto<ExhibitionDetailResponseDto>> createExhibition (
            @PathVariable Long venueId,
            @RequestBody ExhibitionsCreateRequestDto request
    ) {
        ResponseDto<ExhibitionDetailResponseDto> data = exhibitionService.createExhibition(venueId, request);

        return ResponseEntity.ok(data);
    }

    // 전시회 전체 조회
    @GetMapping
    public ResponseEntity<ResponseDto<List<ExhibitionSummaryDto>>> getAllExhibition (
            @PathVariable Long venueId
    ) {
        ResponseDto<List<ExhibitionSummaryDto>> data = exhibitionService.getAllExhibition(venueId);

        return ResponseEntity.ok(data);
    }

    // 전시회 단건 조회
    @GetMapping(ApiMappingPattern.Exhibitions.BY_ID)
    public ResponseEntity<ResponseDto<ExhibitionDetailResponseDto>> getByIdExhibition (
            @PathVariable Long venueId,
            @PathVariable Long exhibitionId
    ) {
        ResponseDto<ExhibitionDetailResponseDto> data = exhibitionService.getByIdExhibition(venueId, exhibitionId);

        return ResponseEntity.ok(data);
    }

    // 삭제
    @DeleteMapping(ApiMappingPattern.Exhibitions.BY_ID)
    public ResponseEntity<ResponseDto<Void>> deleteExhibition (
            @PathVariable Long venueId,
            @PathVariable Long exhibitionId
    ) {
        ResponseDto<Void> data = exhibitionService.deleteExhibition(venueId, exhibitionId);

        return ResponseEntity.ok(data);
    }

    // 전시회 수정
    @PutMapping(ApiMappingPattern.Exhibitions.BY_ID)
    public ResponseEntity<ResponseDto<ExhibitionDetailResponseDto>> updateExhibition (
            @PathVariable Long venueId,
            @PathVariable Long exhibitionId,
            @RequestBody ExhibitionsUpdateRequestDto request
            ) {
        ResponseDto<ExhibitionDetailResponseDto> data = exhibitionService.updateExhibition(venueId, exhibitionId, request);

        return ResponseEntity.ok(data);
    }

    // 전시회 상태 수정
    @PutMapping(ApiMappingPattern.Exhibitions.STATUS)
    public ResponseEntity<ResponseDto<ExhibitionDetailResponseDto>> changeStatusExhibition (
            @PathVariable Long venueId,
            @PathVariable Long exhibitionId,
            @RequestBody ExhibitionsStatusUpdateRequestDto request
    ) {
        ResponseDto<ExhibitionDetailResponseDto> data = exhibitionService.changeStatusExhibition(venueId, exhibitionId, request);

        return ResponseEntity.ok(data);
    }
}
