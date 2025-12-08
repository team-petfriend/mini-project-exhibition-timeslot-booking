package org.example.exhibitiontimeslotbooking.controller.venue;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.page.response.PageResponseDto;
import org.example.exhibitiontimeslotbooking.dto.venues.request.VenuesCreateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.venues.request.VenuesUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.venues.response.VenueDetailResponseDto;
import org.example.exhibitiontimeslotbooking.dto.venues.response.VenueSummaryDto;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.service.file.VenueFileServiceImpl;
import org.example.exhibitiontimeslotbooking.service.venue.VenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.Venues.ROOT)
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;
    private final VenueFileServiceImpl venueFileService;

    // 생성
    @PostMapping
    public ResponseEntity<ResponseDto<VenueDetailResponseDto>> createVenue(@Valid @RequestBody VenuesCreateRequestDto request)
    {
        ResponseDto<VenueDetailResponseDto> data = venueService.createVenue(request);

        return ResponseEntity.ok(data);
    }

    // 전체조회
    @GetMapping
    public ResponseEntity<ResponseDto<PageResponseDto<VenueSummaryDto>>> getAllVenues(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size,
            @RequestParam(required = false) String[] sort
    ) {
        ResponseDto<PageResponseDto<VenueSummaryDto>> data = venueService.getAllVenues(page, size, sort);

        return ResponseEntity.ok(data);
    }

    // 단건조회
    @GetMapping(ApiMappingPattern.Venues.ID_ONLY)
    public ResponseEntity<ResponseDto<VenueDetailResponseDto>> getByIdVenue(@PathVariable Long venueId) {
        ResponseDto<VenueDetailResponseDto> data = venueService.getByIdVenue(venueId);

        return ResponseEntity.ok(data);
    }

    // 수정
    @PutMapping(ApiMappingPattern.Venues.ID_ONLY)
    public ResponseEntity<ResponseDto<VenueDetailResponseDto>> updateVenue(
            @PathVariable Long venueId,
            @RequestBody VenuesUpdateRequestDto request
            ) {
        ResponseDto<VenueDetailResponseDto> data = venueService.updateVenue(venueId, request);

        return ResponseEntity.ok(data);
    }

    // 삭제
    @DeleteMapping(ApiMappingPattern.Venues.ID_ONLY)
    public ResponseEntity<ResponseDto<Void>> deleteVenue(@PathVariable Long venueId) {
        ResponseDto<Void> data = venueService.deleteVenue(venueId);

        return ResponseEntity.ok(data);
    }

    @PostMapping(ApiMappingPattern.Venues.VENUE_FILE)
    public ResponseEntity<ResponseDto<?>> uploadVenueFile(
            @PathVariable Long venueId,
            @RequestParam("file")MultipartFile file
            ) {
        ResponseDto<FileInfo> data = venueFileService.updateVenueFile(venueId, file);

        return ResponseEntity.ok(data);
    }
}
