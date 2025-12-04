package org.example.exhibitiontimeslotbooking.service.file;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.entity.venue.Venue;
import org.example.exhibitiontimeslotbooking.repository.file.FileInfoRepository;
import org.example.exhibitiontimeslotbooking.repository.venue.VenueRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VenueFileServiceImpl {
    private final FileInfoRepository fileInfoRepository;
    private final VenueRepository venueRepository;
    private final FileServiceImpl fileService;

    // 파일 생성
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseDto<FileInfo>  updateVenueFile(Long venueId, MultipartFile file) {

        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new EntityNotFoundException("해당 venue의 ID가 존재하지 않습니다. : " + venueId));

        if (venue.getFileInfo() != null) {
            FileInfo current = fileInfoRepository.findById(venue.getFileInfo().getId())
                    .orElse(null);

            if (current != null) {
                fileService.deleteFile(current);
            }
        }

        FileInfo saved = fileService.saveVenueImg(file);

        venue.changedFile(saved);
        venueRepository.save(venue);

        return ResponseDto.success("전시장 파일을 업로드했습니다.", null);
    }
}
