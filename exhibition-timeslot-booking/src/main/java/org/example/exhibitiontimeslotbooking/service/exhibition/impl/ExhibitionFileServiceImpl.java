package org.example.exhibitiontimeslotbooking.service.exhibition.impl;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.errors.ErrorCode;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.dto.exbitions_file.request.ExhibitionsFileUpdateRequestDto;
import org.example.exhibitiontimeslotbooking.dto.exbitions_file.response.ExhibitionFileResponseDto;
import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;
import org.example.exhibitiontimeslotbooking.entity.file.ExhibitionFile;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.exception.BusinessException;
import org.example.exhibitiontimeslotbooking.repository.exhibition.ExhibitionRepository;
import org.example.exhibitiontimeslotbooking.repository.file.ExhibitionFileRepository;
import org.example.exhibitiontimeslotbooking.repository.file.FileInfoRepository;
import org.example.exhibitiontimeslotbooking.service.exhibition.ExhibitionFileService;
import org.example.exhibitiontimeslotbooking.service.file.FileServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExhibitionFileServiceImpl implements ExhibitionFileService {

    private final FileServiceImpl fileService;
    private final ExhibitionFileRepository exhibitionFileRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final FileInfoRepository fileInfoRepository;

    private final int MAX_ATTACH = 4;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN', 'STAFF')")
    public ResponseDto<Void> uploadExhibitionFiles(Long exhibitionId, List<MultipartFile> files) {
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EXHIBITION_NOT_FOUND));

        if (files.size() > MAX_ATTACH) {
            throw new BusinessException(ErrorCode.FILE_CHECK_MAX);
        }

        int order = 0;
        for (MultipartFile mf : files) {
            if (mf == null || mf.isEmpty()) {
                continue;
            }

            FileInfo fileInfo = fileService.saveExhibitionImg(exhibitionId, mf);

            if (fileInfo == null) continue;

            ExhibitionFile exhibitionFile = ExhibitionFile.builder()
                    .exhibition(exhibition)
                    .fileInfo(fileInfo)
                    .displayOrder(order++)
                    .build();

            exhibitionFileRepository.save(exhibitionFile);
        }

        return ResponseDto.success("전시회장 파일 업로드에 성공했습니다.");
    }


    @Override
    @PreAuthorize("permitAll()")
    public ResponseDto<List<ExhibitionFileResponseDto>> getByIdExhibitionFiles(Long exhibitionId) {

        List<ExhibitionFile> exhibitionFiles = exhibitionFileRepository.findByExhibitionIdOrderByDisplayOrderAsc(exhibitionId);

        List<ExhibitionFileResponseDto> data = exhibitionFiles.stream()
                .map(ExhibitionFile::getFileInfo)
                .filter(Objects::nonNull)
                .map(ExhibitionFileResponseDto::from)
                .toList();

        return ResponseDto.success("전시회장 파일 조회를 성공했습니다.", data);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto<Void> deleteExhibitionFile(Long exhibitionId, Long fileId) {

        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new BusinessException(ErrorCode.EXHIBITION_NOT_FOUND));

        ExhibitionFile exhibitionFile = exhibitionFileRepository.findByFileInfoId(fileId)
                .orElseThrow(() -> new BusinessException(ErrorCode.FILE_NOT_FOUND));

        FileInfo fileInfo = exhibitionFile.getFileInfo();

        exhibitionFileRepository.delete(exhibitionFile);
        fileService.deleteFile(fileInfo);

        return ResponseDto.success("파일이 성공적으로 삭제되었습니다.");
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto<Void> updateExhibitionFiles(Long exhibitionId, ExhibitionsFileUpdateRequestDto request) {

        List<MultipartFile> newFiles = request.newFiles();

        List<ExhibitionFile> currentFiles = exhibitionFileRepository.findByExhibitionIdOrderByDisplayOrderAsc(exhibitionId);

        List<ExhibitionFile> deleteTargets = currentFiles.stream()
                .filter(exhibitionFile -> {
                    FileInfo info = exhibitionFile.getFileInfo();
                    return info == null;
                })
                .toList();

        for (ExhibitionFile ef : deleteTargets) {
            exhibitionFileRepository.delete(ef);
            FileInfo info = ef.getFileInfo();
            if (info != null) {
                fileService.deleteFile(info);
            }
        }

        if (newFiles != null && !newFiles.isEmpty()) {
            Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                    .orElseThrow(() -> new BusinessException(ErrorCode.EXHIBITION_NOT_FOUND));

            int maxOrder = exhibitionFileRepository.findByExhibitionIdOrderByDisplayOrderAsc(exhibitionId)
                    .stream()
                    .mapToInt(ExhibitionFile::getDisplayOrder)
                    .max()
                    .orElse(-1);

            for (MultipartFile mf : newFiles) {
                FileInfo fileInfo = fileService.saveExhibitionImg(exhibitionId, mf);
                ExhibitionFile ef = ExhibitionFile.builder()
                        .exhibition(exhibition)
                        .fileInfo(fileInfo)
                        .displayOrder(++maxOrder)
                        .build();

                exhibitionFileRepository.save(ef);
            }
        }
        return ResponseDto.success("파일이 수정되었습니다.");
    }
}
