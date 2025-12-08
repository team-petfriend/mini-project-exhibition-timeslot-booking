package org.example.exhibitiontimeslotbooking.service.venue.impl;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.errors.ErrorCode;
import org.example.exhibitiontimeslotbooking.common.utils.pageable.PageableUtils;
import org.example.exhibitiontimeslotbooking.common.utils.pageable.SortFields;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.page.response.PageResponseDto;
import org.example.exhibitiontimeslotbooking.dto.venues.request.VenuesCreateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.venues.request.VenuesUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.venues.response.VenueDetailResponseDto;
import org.example.exhibitiontimeslotbooking.dto.venues.response.VenueSummaryDto;
import org.example.exhibitiontimeslotbooking.entity.venue.Venue;
import org.example.exhibitiontimeslotbooking.exception.BusinessException;
import org.example.exhibitiontimeslotbooking.repository.venue.VenueRepository;
import org.example.exhibitiontimeslotbooking.service.venue.VenueService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    // 생성
    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto<VenueDetailResponseDto> createVenue(VenuesCreateRequestDto request) {
        Venue venue = Venue.builder()
                        .name(request.name())
                        .address(request.address())
                        .latitude(request.latitude())
                        .longitude(request.longitude())
                        .build();

        Venue saved = venueRepository.save(venue);

        VenueDetailResponseDto data = VenueDetailResponseDto.from(saved);

        return ResponseDto.success("전시장이 생성되었습니다.", data);
    }

    // 조회
    @Override
    @PreAuthorize("permitAll()")
    public ResponseDto<VenueDetailResponseDto> getByIdVenue(Long venueId) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));

        VenueDetailResponseDto data = VenueDetailResponseDto.from(venue);

        return ResponseDto.success("전시장 조회 되었습니다.", data);
    }

    // 수정
    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseDto<VenueDetailResponseDto> updateVenue(Long venueId, VenuesUpdateRequestDto request) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));

        venue.changedVenue(
                request.name(),
                request.address(),
                request.latitude(),
                request.latitude()
        );

        venueRepository.flush();

        VenueDetailResponseDto data = VenueDetailResponseDto.from(venue);

        return ResponseDto.success("전시장이 수정되었습니다.", data);
    }

    // 삭제
    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto<Void> deleteVenue(Long venueId) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));

        venueRepository.delete(venue);

        return ResponseDto.success("전시장이 삭제되었습니다.", null);
    }
}
