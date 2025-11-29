package org.example.exhibitiontimeslotbooking.controller.timeslot;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.constants.ApiMappingPattern;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.timeslots.request.TimeslotCreateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.timeslots.request.TimeslotStatusChangeRequestDto;
import org.example.exhibitiontimeslotbooking.dto.timeslots.request.TimeslotUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.timeslots.response.TimeslotDetailResponseDto;
import org.example.exhibitiontimeslotbooking.repository.timeslot.TimeslotRepository;
import org.example.exhibitiontimeslotbooking.service.timeslot.TimeslotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.Timeslots.ROOT)
@RequiredArgsConstructor
public class TimeSlotController {
    private final TimeslotService timeslotService;

    // 생성
    @PostMapping
    public ResponseEntity<ResponseDto<TimeslotDetailResponseDto>> createTimeslot(
            @PathVariable Long venueId,
            @PathVariable Long exhibitionId,
            @RequestBody TimeslotCreateRequestDto request
    ) {

        ResponseDto<TimeslotDetailResponseDto> data = timeslotService.createTimeslot(venueId, exhibitionId, request);

        return ResponseEntity.ok(data);
    }

    // 전체조회
    @GetMapping
    public ResponseEntity<ResponseDto<List<TimeslotDetailResponseDto>>> getAllTimeslot(
            @PathVariable Long venueId,
            @PathVariable Long exhibitionId
    ) {

        ResponseDto<List<TimeslotDetailResponseDto>> data = timeslotService.getAllTimeslot(venueId, exhibitionId);

        return ResponseEntity.ok(data);
    }

    // 단건조회
    @GetMapping(ApiMappingPattern.Timeslots.ID_ONLY)
    public ResponseEntity<ResponseDto<TimeslotDetailResponseDto>> getByIdTimeslot(
            @PathVariable Long venueId,
            @PathVariable Long exhibitionId,
            @PathVariable Long timeslotId
    ) {
        ResponseDto<TimeslotDetailResponseDto> data = timeslotService.getByIdTimeslot(venueId, exhibitionId, timeslotId);

        return ResponseEntity.ok(data);
    }

    // 수정
    @PutMapping(ApiMappingPattern.Timeslots.ID_ONLY)
    public ResponseEntity<ResponseDto<TimeslotDetailResponseDto>> updateTimeslot(
            @PathVariable Long venueId,
            @PathVariable Long exhibitionId,
            @PathVariable Long timeslotId,
            @RequestBody TimeslotUpdateRequestDto request
            ) {
        ResponseDto<TimeslotDetailResponseDto> data = timeslotService.updateTimeslot(venueId, exhibitionId, timeslotId, request);

        return ResponseEntity.ok(data);
    }

    // 삭제
    @DeleteMapping(ApiMappingPattern.Timeslots.ID_ONLY)
    public ResponseEntity<ResponseDto<Void>> deleteTimeslot(
            @PathVariable Long venueId,
            @PathVariable Long exhibitionId,
            @PathVariable Long timeslotId
    ) {
        ResponseDto<Void> data = timeslotService.deleteTimeslot(venueId, exhibitionId, timeslotId);

        return ResponseEntity.ok(data);
    }

    // 상태 수정
    @PutMapping(ApiMappingPattern.Timeslots.STATUS)
    public ResponseEntity<ResponseDto<TimeslotDetailResponseDto>> changeTimeslot(
            @PathVariable Long venueId,
            @PathVariable Long exhibitionId,
            @PathVariable Long timeslotId,
            @RequestBody TimeslotStatusChangeRequestDto request
    ) {
        ResponseDto<TimeslotDetailResponseDto> data = timeslotService.changeTimeslot(venueId, exhibitionId, timeslotId, request);

        return ResponseEntity.ok(data);
    }

}
