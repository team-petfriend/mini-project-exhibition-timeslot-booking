package org.example.exhibitiontimeslotbooking.service.venue;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.page.response.PageResponseDto;
import org.example.exhibitiontimeslotbooking.dto.venues.request.VenuesCreateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.venues.request.VenuesUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.venues.response.VenueDetailResponseDto;
import org.example.exhibitiontimeslotbooking.dto.venues.response.VenueSummaryDto;

import java.util.List;

public interface VenueService {
    ResponseDto<VenueDetailResponseDto> createVenue(@Valid VenuesCreateRequestDto request);
    ResponseDto<VenueDetailResponseDto> getByIdVenue(Long venueId);
    ResponseDto<VenueDetailResponseDto> updateVenue(Long venueId, VenuesUpdateRequestDto request);
    ResponseDto<Void> deleteVenue(Long venueId);
    ResponseDto<PageResponseDto<VenueSummaryDto>> getAllVenues(@Min(0) int page, @Min(1) @Max(100) int size, String[] sort);
}
