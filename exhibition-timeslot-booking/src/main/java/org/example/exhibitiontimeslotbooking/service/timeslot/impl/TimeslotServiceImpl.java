package org.example.exhibitiontimeslotbooking.service.timeslot.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exhibitiontimeslotbooking.common.enums.errors.ErrorCode;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.CapacityPolicy;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.ExhibitionStatus;
import org.example.exhibitiontimeslotbooking.common.enums.slots.SlotStatus;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.timeslots.request.TimeslotCreateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.timeslots.request.TimeslotStatusChangeRequestDto;
import org.example.exhibitiontimeslotbooking.dto.timeslots.request.TimeslotUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.timeslots.response.TimeslotDetailResponseDto;
import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;
import org.example.exhibitiontimeslotbooking.entity.timeslot.Timeslot;
import org.example.exhibitiontimeslotbooking.entity.venue.Venue;
import org.example.exhibitiontimeslotbooking.exception.BusinessException;
import org.example.exhibitiontimeslotbooking.repository.exhibition.ExhibitionRepository;
import org.example.exhibitiontimeslotbooking.repository.timeslot.TimeslotRepository;
import org.example.exhibitiontimeslotbooking.repository.venue.VenueRepository;
import org.example.exhibitiontimeslotbooking.service.timeslot.TimeslotService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TimeslotServiceImpl implements TimeslotService {
    private final VenueRepository venueRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final TimeslotRepository timeslotRepository;

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseDto<TimeslotDetailResponseDto> createTimeslot(Long venueId, Long exhibitionId, TimeslotCreateRequestDto request) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));

        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EXHIBITION_NOT_FOUND));

        if (exhibition.getExhibitionStatus() == ExhibitionStatus.CANCELED) {
            throw new BusinessException(ErrorCode.EXHIBITION_ALREADY_CANCELED);
        }

        if (exhibition.getExhibitionStatus() == ExhibitionStatus.CLOSED) {
            throw new BusinessException(ErrorCode.EXHIBITION_IS_CLOSED);
        }

        if (exhibition.getCapacityPolicy() == CapacityPolicy.PER_DAY) {
            if (!exhibition.getTimeslots().isEmpty()) {
                throw new BusinessException(ErrorCode.TIMESLOT_ALREADY_EXISTS);
            }
        }

        Timeslot timeslot =  Timeslot.builder()
                .startTime(request.startTime())
                .endTime(request.endTime())
                .capacity(request.capacity())
                .reserved(request.reserved())
                .slotsStatus(SlotStatus.OPEN)
                .exhibition(exhibition)
                .build();

        timeslotRepository.save(timeslot);

        timeslotRepository.flush();

        TimeslotDetailResponseDto data = TimeslotDetailResponseDto.from(timeslot);

        return ResponseDto.success("타임이 생성되었습니다.", data);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'USER')")
    public ResponseDto<List<TimeslotDetailResponseDto>> getAllTimeslot(Long venueId, Long exhibitionId) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));

        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EXHIBITION_NOT_FOUND));

        List<Timeslot> timeslots = timeslotRepository.findAll();

        List<TimeslotDetailResponseDto> data = timeslots.stream()
                .map(TimeslotDetailResponseDto::from)
                .toList();

        return ResponseDto.success("타임슬롯이 전체조회되었습니다.", data);
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'USER')")
    public ResponseDto<TimeslotDetailResponseDto> getByIdTimeslot(Long venueId, Long exhibitionId, Long slotId) {
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));

        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EXHIBITION_NOT_FOUND));

        Timeslot timeslot = timeslotRepository.findById(slotId)
                .orElseThrow(() -> new BusinessException(ErrorCode.TIMESLOT_NOT_FOUND));

        TimeslotDetailResponseDto data = TimeslotDetailResponseDto.from(timeslot);

        return ResponseDto.success("타임슬롯이 조회되었습니다", data);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseDto<TimeslotDetailResponseDto> updateTimeslot(Long venueId, Long exhibitionId, Long slotId, TimeslotUpdateRequestDto request) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));

        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EXHIBITION_NOT_FOUND));

        Timeslot timeslot = timeslotRepository.findById(slotId)
                .orElseThrow(() -> new BusinessException(ErrorCode.TIMESLOT_NOT_FOUND));

        if (timeslot.getSlotsStatus() == SlotStatus.CANCELED) {
            throw new BusinessException(ErrorCode.TIMESLOT_ALREADY_CANCELED);
        }

        timeslot.updated(
                request.startTime(),
                request.endTime(),
                request.capacity()
        );

        timeslotRepository.flush();

        TimeslotDetailResponseDto data = TimeslotDetailResponseDto.from(timeslot);

        return ResponseDto.success("타임슬롯이 수정되었습니다.", data);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto<Void> deleteTimeslot(Long venueId, Long exhibitionId, Long slotId) {
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));

        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EXHIBITION_NOT_FOUND));

        Timeslot timeslot = timeslotRepository.findById(slotId)
                .orElseThrow(() -> new BusinessException(ErrorCode.TIMESLOT_NOT_FOUND));

         timeslotRepository.deleteById(slotId);

        return ResponseDto.success("타임슬롯이 삭제되었습니다.", null);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseDto<TimeslotDetailResponseDto> changeTimeslot(Long venueId, Long exhibitionId, Long slotId, TimeslotStatusChangeRequestDto request) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));

        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EXHIBITION_NOT_FOUND));

        Timeslot timeslot = timeslotRepository.findById(slotId)
                .orElseThrow(() -> new BusinessException(ErrorCode.TIMESLOT_NOT_FOUND));

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime startTime = timeslot.getStartTime();
        LocalDateTime endTime = timeslot.getEndTime();

        if (timeslot.getSlotsStatus() == SlotStatus.CANCELED) {
            throw new BusinessException(ErrorCode.TIMESLOT_ALREADY_CANCELED);
        }

        if (today.isAfter(startTime) && today.isBefore(endTime)) {
            throw new BusinessException(ErrorCode.TIMESLOT_OUT_OF_EXHIBITION_RANGE);
        }

        timeslot.checkStatus(
                request.timeslotstatus()
        );

        TimeslotDetailResponseDto data = TimeslotDetailResponseDto.from(timeslot);

        return ResponseDto.success("타임슬롯 상태가 변경되었습니다.", data);
    }

    @Override
    @Transactional
    public void autoUpdateTimeslotStatus() {

       List<Timeslot> timeslots = timeslotRepository.findAll();

       LocalDateTime now = LocalDateTime.now();

        for (Timeslot ts : timeslots ) {
            if (ts.getSlotsStatus() == SlotStatus.CANCELED) {
                continue;
            }

           if (ts.getStartTime().isAfter(now) || ts.getEndTime().isBefore(now)) {
               ts.checkStatus(SlotStatus.CLOSED);
           } else if (!ts.getStartTime().isAfter(now) && !ts.getEndTime().isBefore(now)) {
               ts.checkStatus(SlotStatus.OPEN);
           }
       }
        ResponseDto.success("타임슬롯이 자동 업데이트 되었습니다.", null);
    }

}
