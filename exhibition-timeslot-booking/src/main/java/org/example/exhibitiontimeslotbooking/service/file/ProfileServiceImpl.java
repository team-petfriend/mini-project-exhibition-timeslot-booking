package org.example.exhibitiontimeslotbooking.service.file;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.dto.ResponseDto;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.entity.user.User;
import org.example.exhibitiontimeslotbooking.repository.file.FileInfoRepository;
import org.example.exhibitiontimeslotbooking.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl {
    private final FileInfoRepository fileInfoRepository;
    private final UserRepository userRepository;
    private final FileServiceImpl fileService;

    @Transactional
    public ResponseDto<FileInfo> updateProfile(Long userId, MultipartFile file) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        if (user.getProfileFile() != null) {
            FileInfo current = fileInfoRepository.findById(user.getProfileFile().getId())
                    .orElse(null);

            if (current != null) {
                fileService.deleteFile(current);
            }
        }

        FileInfo saved = fileService.saveUserProfileImage(file);

        user.updateProfileImage(saved);
        userRepository.save(user);

        return ResponseDto.success(saved);
    }
}
