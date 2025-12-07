package org.example.exhibitiontimeslotbooking.service.exhibition.impl;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.errors.ErrorCode;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.CapacityPolicy;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.ExhibitionStatus;
import org.example.exhibitiontimeslotbooking.common.utils.pageable.PageableUtils;
import org.example.exhibitiontimeslotbooking.common.utils.pageable.SortFields;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.exbitions_file.response.ExhibitionFileResponseDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.request.ExhibitionsCreateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.request.ExhibitionsStatusUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.request.ExhibitionsUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.response.ExhibitionDetailResponseDto;
import org.example.exhibitiontimeslotbooking.dto.exhibitions.response.ExhibitionSummaryDto;
import org.example.exhibitiontimeslotbooking.dto.page.response.PageResponseDto;
import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;
import org.example.exhibitiontimeslotbooking.entity.file.ExhibitionFile;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.entity.venue.Venue;
import org.example.exhibitiontimeslotbooking.exception.BusinessException;
import org.example.exhibitiontimeslotbooking.repository.exhibition.ExhibitionRepository;
import org.example.exhibitiontimeslotbooking.repository.file.ExhibitionFileRepository;
import org.example.exhibitiontimeslotbooking.repository.file.FileInfoRepository;
import org.example.exhibitiontimeslotbooking.repository.venue.VenueRepository;
import org.example.exhibitiontimeslotbooking.service.exhibition.ExhibitionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExhibitionServiceImpl implements ExhibitionService {

    private final VenueRepository venueRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final FileInfoRepository fileInfoRepository;
    private final ExhibitionFileRepository exhibitionFileRepository;

        @Override
        @Transactional
        @PreAuthorize("hasRole('ADMIN')")
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

            int order = 0;
            if (request.fileIds() != null && !request.fileIds().isEmpty()) {
                List<FileInfo> fileInfos = fileInfoRepository.findAllById(request.fileIds());


                if (fileInfos.size() != request.fileIds().size()) {
                    throw new BusinessException(ErrorCode.FILE_NOT_FOUND);
                }

                for (FileInfo file : fileInfos) {
                    ExhibitionFile ef = ExhibitionFile.builder()
                            .exhibition(saved)
                            .fileInfo(file)
                            .displayOrder(order++)
                            .build();

                    exhibitionFileRepository.save(ef);
                }
            }

            ExhibitionDetailResponseDto data = ExhibitionDetailResponseDto.from(saved);

            return ResponseDto.success("전시회가 생성되었습니다.", data);
        }
    
    @Override
    @PreAuthorize("permitAll()")
    public ResponseDto<PageResponseDto<ExhibitionSummaryDto>> getAllExhibition(Long venueId, int page, int size, String[] sort) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));

        Pageable pageable = PageableUtils.buildPageable(page, size, sort, SortFields.EXHIBITION_SORTS);

        Page<Exhibition> exhibitionPage = exhibitionRepository.findAll(pageable);

        List<ExhibitionSummaryDto> PageList = exhibitionPage.getContent().stream()
                .map(ExhibitionSummaryDto::from)
                .toList();

        PageResponseDto<ExhibitionSummaryDto> data = PageResponseDto.<ExhibitionSummaryDto>builder()
                .content(PageList)
                .currentPage(exhibitionPage.getNumber())
                .totalPages(exhibitionPage.getTotalPages())
                .totalElements(exhibitionPage.getTotalElements())
                .build();

        return ResponseDto.success("전시회가 전체조회되었습니다.", data);
    }

    @Override
    @PreAuthorize("permitAll()")
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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
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
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
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
    @PreAuthorize("permitAll()")
    public ResponseDto<PageResponseDto<ExhibitionSummaryDto>> searchExhibition(Long venueId, String keyword, int page, int size, String[] sort) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new BusinessException(ErrorCode.VENUE_NOT_FOUND));

        Pageable pageable = PageableUtils.buildPageable(page, size, sort, SortFields.EXHIBITION_SORTS);

        String searchKeyword = "%" + keyword + "%";
        Page<Exhibition> pageResult = exhibitionRepository.searchExhibitionByKeyword(venue.getId(), searchKeyword, pageable);

        List<ExhibitionSummaryDto> content = pageResult.getContent().stream()
                .map(ExhibitionSummaryDto::from)
                .toList();

        PageResponseDto<ExhibitionSummaryDto> data = PageResponseDto.<ExhibitionSummaryDto>builder()
                .content(content)
                .currentPage(pageResult.getNumber())
                .totalPages(pageResult.getTotalPages())
                .totalElements(pageResult.getTotalElements())
                .first(pageResult.isFirst())
                .last(pageResult.isLast())
                .build();

        return ResponseDto.success("검색어를 찾았습니다.", data);
    }

    @Override
    @Transactional
    public void autoUpdateExhibitionStatus() {
        List<Exhibition> exhibitions = exhibitionRepository.findAll();

        LocalDate today = LocalDate.now();

        for (Exhibition es : exhibitions) {

            if (es.getExhibitionStatus() == ExhibitionStatus.CANCELED) {
                continue;
            }

            if (es.getStartDate().isAfter(today)) {
                es.changedStatus(ExhibitionStatus.SCHEDULED);
            } else if (!es.getStartDate().isAfter(today) && !es.getEndDate().isBefore(today)) {
                es.changedStatus(ExhibitionStatus.OPEN);
            } else {
                es.changedStatus(ExhibitionStatus.CLOSED);
            }
        }
        ResponseDto.success("전시회 상태가 자동 업데이트 되었습니다.", null);
    }

}
