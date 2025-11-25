package org.example.exhibitiontimeslotbooking.service.venue;

import jakarta.validation.Valid;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.venues.request.VenuesCreateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.venues.request.VenuesUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.venues.response.VenueDetailResponseDto;
import org.example.exhibitiontimeslotbooking.dto.venues.response.VenueSummaryDto;

import java.util.List;

public interface VenueService {
    ResponseDto<VenueDetailResponseDto> createVenue(@Valid VenuesCreateRequestDto request);

    ResponseDto<List<VenueSummaryDto>> getAllVenues();

    ResponseDto<VenueDetailResponseDto> getByIdVenue(Long venueId);

    ResponseDto<VenueDetailResponseDto> updateVenue(Long venueId, VenuesUpdateRequestDto request);

    ResponseDto<Void> deleteVenue(Long venueId);
}
