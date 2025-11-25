package org.example.exhibitiontimeslotbooking.service.venue.impl;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.venues.request.VenuesCreateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.venues.request.VenuesUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.venues.response.VenueDetailResponseDto;
import org.example.exhibitiontimeslotbooking.dto.venues.response.VenueSummaryDto;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.entity.venue.Venue;
import org.example.exhibitiontimeslotbooking.repository.file.FileInfoRepository;
import org.example.exhibitiontimeslotbooking.repository.venue.VenueRepository;
import org.example.exhibitiontimeslotbooking.service.venue.VenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    // 생성
    @Override
    @Transactional
    public ResponseDto<VenueDetailResponseDto> createVenue(VenuesCreateRequestDto request) {

        Venue venue = Venue.builder()
                        .name(request.name())
                        .address(request.address())
                        .latitude(request.latitude())
                        .longitude(request.longitude())
                        .build();

        Venue saved = venueRepository.save(venue);

        VenueDetailResponseDto data = VenueDetailResponseDto.from(saved);

        return ResponseDto.setSuccess("SUCCESS", data);
    }

    // 전체 조회
    @Override
    public ResponseDto<List<VenueSummaryDto>> getAllVenues() {

        List<Venue> venues = venueRepository.findAll();

        List<VenueSummaryDto> data = venues.stream()
                .map(VenueSummaryDto::from)
                .toList();

        return ResponseDto.setSuccess("전체조회에 성공하였습니다.", data);
    }

    // 조회
    @Override
    public ResponseDto<VenueDetailResponseDto> getByIdVenue(Long venueId) {
        if (venueId == null) throw new CustomEx

        return null;
    }

    // 수정
    @Override
    public ResponseDto<VenueDetailResponseDto> updateVenue(Long venueId, VenuesUpdateRequestDto request) {
        return null;
    }

    // 삭제
    @Override
    public ResponseDto<Void> deleteVenue(Long venueId) {
        return null;
    }
}
