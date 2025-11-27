package org.example.exhibitiontimeslotbooking.controller.venue;


import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.venues.request.VenuesCreateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.venues.request.VenuesUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.venues.response.VenueDetailResponseDto;
import org.example.exhibitiontimeslotbooking.dto.venues.response.VenueListResponseDto;
import org.example.exhibitiontimeslotbooking.dto.venues.response.VenueSummaryDto;
import org.example.exhibitiontimeslotbooking.service.venue.VenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.Venues.ROOT)
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    // 생성
    @PostMapping
    public ResponseEntity<ResponseDto<VenueDetailResponseDto>> createVenue(@Valid @RequestBody VenuesCreateRequestDto request)
    {
        ResponseDto<VenueDetailResponseDto> data = venueService.createVenue(request);

        return ResponseEntity.ok(data);
    }

    // 전체조회
    @GetMapping
    public ResponseEntity<ResponseDto<List<VenueSummaryDto>>> getAllVenues() {
        ResponseDto<List<VenueSummaryDto>> data = venueService.getAllVenues();

        return ResponseEntity.ok(data);
    }

    // 단건조회
    @GetMapping(ApiMappingPattern.Venues.BY_ID)
    public ResponseEntity<ResponseDto<VenueDetailResponseDto>> getByIdVenue(@PathVariable Long venueId) {
        ResponseDto<VenueDetailResponseDto> data = venueService.getByIdVenue(venueId);

        return ResponseEntity.ok(data);
    }

    // 수정
    @PutMapping(ApiMappingPattern.Venues.BY_ID)
    public ResponseEntity<ResponseDto<VenueDetailResponseDto>> updateVenue(
            @PathVariable Long venueId,
            @RequestBody VenuesUpdateRequestDto request
            ) {
        ResponseDto<VenueDetailResponseDto> data = venueService.updateVenue(venueId, request);

        return ResponseEntity.ok(data);
    }

    // 삭제
    @GetMapping(ApiMappingPattern.Venues.BY_ID)
    public ResponseEntity<ResponseDto<Void>> deleteVenue(@PathVariable Long venueId ) {
        ResponseDto<Void> data = venueService.deleteVenue(venueId);

        return ResponseEntity.ok(data);
    }

}
