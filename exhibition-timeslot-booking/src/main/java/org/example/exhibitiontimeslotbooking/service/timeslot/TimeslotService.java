package org.example.exhibitiontimeslotbooking.service.timeslot;

import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.timeslots.request.TimeslotCreateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.timeslots.request.TimeslotStatusChangeRequestDto;
import org.example.exhibitiontimeslotbooking.dto.timeslots.request.TimeslotUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.timeslots.response.TimeslotDetailResponseDto;

import java.util.List;

public interface TimeslotService {
    ResponseDto<TimeslotDetailResponseDto> createTimeslot(Long venueId, Long exhibitionId, TimeslotCreateRequestDto request);

    ResponseDto<List<TimeslotDetailResponseDto>> getAllTimeslot(Long venueId, Long exhibitionId);

    ResponseDto<TimeslotDetailResponseDto> getByIdTimeslot(Long venueId, Long exhibitionId, Long timeslotId);

    ResponseDto<TimeslotDetailResponseDto> updateTimeslot(Long venueId, Long exhibitionId, Long timeslotId, TimeslotUpdateRequestDto request);

    ResponseDto<Void> deleteTimeslot(Long venueId, Long exhibitionId, Long timeslotId);

    ResponseDto<TimeslotDetailResponseDto> changeTimeslot(Long venueId, Long exhibitionId, Long timeslotId, TimeslotStatusChangeRequestDto request);

    void autoUpdateTimeslotStatus();
}
