package org.example.exhibitiontimeslotbooking.service.exhibition;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.request.ExhibitionsCreateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.request.ExhibitionsStatusUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.request.ExhibitionsUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.response.ExhibitionDetailResponseDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.response.ExhibitionSummaryDto;

import java.util.List;

public interface ExhibitionService {

    ResponseDto<ExhibitionDetailResponseDto> getByIdExhibition(Long venueId, Long exhibitionId);

    ResponseDto<ExhibitionDetailResponseDto> updateExhibition(Long venueId, Long exhibitionId, ExhibitionsUpdateRequestDto request);

    ResponseDto<ExhibitionDetailResponseDto> changeStatusExhibition(Long venueId, Long exhibitionId, ExhibitionsStatusUpdateRequestDto request);

    ResponseDto<ExhibitionDetailResponseDto> createExhibition(Long venueId, ExhibitionsCreateRequestDto request);


    ResponseDto<Void> deleteExhibition(Long venueId, Long exhibitionId);

    void autoUpdateExhibitionStatus();

    ResponseDto<List<ExhibitionSummaryDto>> getAllExhibition(Long venueId, @Min(0) int page, @Min(1) @Max(100) int size, String[] sort);
}
