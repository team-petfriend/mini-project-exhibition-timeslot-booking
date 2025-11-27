package org.example.exhibitiontimeslotbooking.service.exhibition.impl;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.errors.ErrorCode;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.CapacityPolicy;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.ExhibitionStatus;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.request.ExhibitionsCreateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.request.ExhibitionsStatusUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.request.ExhibitionsUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.response.ExhibitionDetailResponseDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.response.ExhibitionSummaryDto;
import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;
import org.example.exhibitiontimeslotbooking.entity.venue.Venue;
import org.example.exhibitiontimeslotbooking.exception.BusinessException;
import org.example.exhibitiontimeslotbooking.repository.exhibition.ExhibitionRepository;
import org.example.exhibitiontimeslotbooking.repository.venue.VenueRepository;
import org.example.exhibitiontimeslotbooking.service.exhibition.ExhibitionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExhibitionServiceImpl implements ExhibitionService {

    private final VenueRepository venueRepository;
    private final ExhibitionRepository exhibitionRepository;

    @Override
    @Transactional
    public ResponseDto<ExhibitionDetailResponseDto> createExhibition(Long venueId, ExhibitionsCreateRequestDto request) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));

        Exhibition exhibition = Exhibition.builder()
                .title(request.title())
                .description(request.description())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .exhibitionStatus(ExhibitionStatus.SCHEDULED)
                .capacityPolicy(CapacityPolicy.PER_SLOT)
                .venue(venue)
                .build();

        Exhibition saved = exhibitionRepository.save(exhibition);

        ExhibitionDetailResponseDto data = ExhibitionDetailResponseDto.from(saved);

        return ResponseDto.success("전시회가 생성되었습니다.", data);
    }

    @Override
    public ResponseDto<List<ExhibitionSummaryDto>> getAllExhibition(Long venueId) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));


        List<Exhibition> exhibitions = exhibitionRepository.findAll();

        List<ExhibitionSummaryDto> data = exhibitions.stream()
                .map(ExhibitionSummaryDto::from)
                .toList();


        return ResponseDto.success("전시회장이 전체 조회되었습니다.", data);
    }


    @Override
    public ResponseDto<ExhibitionDetailResponseDto> getByIdExhibition(Long venueId, Long exhibitionId) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));

        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EXHIBITION_NOT_FOUND));

        ExhibitionDetailResponseDto data = ExhibitionDetailResponseDto.from(exhibition);

        return ResponseDto.success("전시회장 조회가 되었습니다.", data);
    }

    @Override
    @Transactional
    public ResponseDto<Void> deleteExhibition(Long venueId, Long exhibitionId) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));

        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EXHIBITION_NOT_FOUND));

        exhibitionRepository.deleteById(exhibitionId);

        return ResponseDto.success("전시회장이 삭제되었습니다.", null);
    }

    @Override
    @Transactional
    public ResponseDto<ExhibitionDetailResponseDto> updateExhibition(Long venueId, Long exhibitionId, ExhibitionsUpdateRequestDto request) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));

        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EXHIBITION_NOT_FOUND));

        exhibition.updated(
                request.title(),
                request.description(),
                request.startDate(),
                request.endDate(),
                request.capacityPolicy()
        );

        exhibitionRepository.flush();

        ExhibitionDetailResponseDto data = ExhibitionDetailResponseDto.from(exhibition);

        return ResponseDto.success("전시회 수정이 되었습니다.", data);
    }

    @Override
    @Transactional
    public ResponseDto<ExhibitionDetailResponseDto> changeStatusExhibition(Long venueId, Long exhibitionId, ExhibitionsStatusUpdateRequestDto request) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));

        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EXHIBITION_NOT_FOUND));

        if (exhibition.getExhibitionStatus() == ExhibitionStatus.CANCELED) {
            throw new BusinessException(ErrorCode.EXHIBITION_ALREADY_CANCELED);
        }

        if (exhibition.getExhibitionStatus() == ExhibitionStatus.CLOSED) {
            throw new BusinessException(ErrorCode.EXHIBITION_STATUS_FINALIZED);
        }

        exhibition.changedStatus(
                request.exhibitionStatus()
        );

        ExhibitionDetailResponseDto data = ExhibitionDetailResponseDto.from(exhibition);

        exhibitionRepository.flush();

        return ResponseDto.success("전시회장 상태가 변경되었습니다.", data);
    }

    @Override
    @Transactional
    public ResponseDto<ExhibitionDetailResponseDto> changeAutoStatusExhibition(Long venueId, Long exhibitionId) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));

        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EXHIBITION_NOT_FOUND));

        LocalDate today = LocalDate.now();
        LocalDate startDate = exhibition.getStartDate();
        LocalDate endDate = exhibition.getEndDate();

        if (exhibition.getExhibitionStatus() == ExhibitionStatus.CANCELED) {
            throw new BusinessException(ErrorCode.EXHIBITION_ALREADY_CANCELED);
        }

        if (exhibition.getExhibitionStatus() == ExhibitionStatus.CLOSED) {
            throw new BusinessException(ErrorCode.EXHIBITION_STATUS_FINALIZED);
        }

        if  (today.isBefore(startDate)) {
            exhibition.changedStatus(ExhibitionStatus.SCHEDULED);
        }

        if (today.isAfter(endDate)) {
            exhibition.changedStatus(ExhibitionStatus.CLOSED);
        }

        if (!today.isBefore(startDate) && !today.isAfter(endDate)) {
            exhibition.changedStatus(ExhibitionStatus.OPEN);
        }

        ExhibitionDetailResponseDto data = ExhibitionDetailResponseDto.from(exhibition);

        return ResponseDto.success("전시회가 자동 수정되었습니다.", data);
    }
}
