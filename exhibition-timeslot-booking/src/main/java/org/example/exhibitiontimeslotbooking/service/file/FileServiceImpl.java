package org.example.exhibitiontimeslotbooking.service.file;

import lombok.RequiredArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.errors.ErrorCode;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.exception.FileStorageException;
import org.example.exhibitiontimeslotbooking.repository.file.FileInfoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl {

    @Value("${file.upload.base-path}")
    private String basePath;

    @Value("${file.upload.user-profile}")
    private String profilePath;

    @Value("${file.upload.venues-img}")
    private String venusPath;

    @Value("${file.upload.reviews-img}")
    private String reviewsPath;

    @Value("${file.upload.exhibitions-img}")
    private String exhibitionsPath;

    private  final FileInfoRepository fileInfoRepository;

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of(
            "jpg", "jpeg", "png", "gif",
            "pdf", "txt", "zip"
    );

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024L;

    /** 파일 유효성 검증 */
    private void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new FileStorageException(ErrorCode.INVALID_INPUT, "빈 파일은 업로드할 수 없습니다.");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new FileStorageException(
                    ErrorCode.INVALID_INPUT,
                    "파일 용량이 너무 큽니다. 최대 10MB까지 업로드 가능합니다."
            );
        }

        String original = file.getOriginalFilename();
        String cleanName = StringUtils.cleanPath(original != null ? original : "");

        // 경로 조작 방지
        if (cleanName.contains("..")) {
            throw new FileStorageException(
                    ErrorCode.INVALID_INPUT,
                    "잘못된 파일 이름입니다."
            );
        }

        // 확장자 체크
        String ext = "";
        int dot = cleanName.lastIndexOf('.');
        if (dot != -1 && dot < cleanName.length() - 1) {
            ext = cleanName.substring(dot + 1).toLowerCase();
        }

        if (!ALLOWED_EXTENSIONS.contains(ext)) {
            throw new FileStorageException(
                    ErrorCode.INVALID_INPUT,
                    "허용되지 않는 파일 형식입니다."
            );
        }
    }

    /** 업로드 디렉토리 생성 */
    private void ensureDirectory(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /** 저장 파일명 생성 */
    private String generateStoredName(String originalName) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid + "_" + originalName;
    }

    /** 실제 업로드 경로 생성 */
    private String buildFullPath(String relativePath, String storedName) {
        return basePath + "/" + relativePath + "/" + storedName;
    }

    /** 프로필 업로드 (1개만 유지) */
    public FileInfo saveUserProfileImage(MultipartFile file) {

        if (file == null || file.isEmpty()) return null;

        validateFile(file);

        try {
            String original = file.getOriginalFilename();
            String cleanName = StringUtils.cleanPath(original);
            String storedName = generateStoredName(cleanName);

            String fullDir = basePath + "/" + profilePath;
            ensureDirectory(fullDir);

            Path path = Paths.get(fullDir + "/" + storedName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            FileInfo info = FileInfo.builder()
                    .originalName(cleanName)
                    .storedName(storedName)
                    .contentType(file.getContentType())
                    .fileSize(file.getSize())
                    .filePath(path.toString())
                    .createdAt(LocalDateTime.now())
                    .build();

            return fileInfoRepository.save(info);

        } catch (Exception e) {
            throw new FileStorageException(ErrorCode.INTERNAL_ERROR, "" ,e);
        }
    }

    public FileInfo saveExhibitionImg(Long exhibitionId, MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return null;
        }

        validateFile(file);

        try {
            String original = file.getOriginalFilename();
            String cleanName = StringUtils.cleanPath(original);
            String storedName = generateStoredName(cleanName);

            String relativePath = exhibitionsPath + "/" + exhibitionId;
            String fullDir = basePath + "/" + relativePath;

            ensureDirectory(fullDir);

            Path path = Paths.get(fullDir + "/" + storedName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            FileInfo info = FileInfo.builder()
                    .originalName(cleanName)
                    .storedName(storedName)
                    .contentType(file.getContentType())
                    .fileSize(file.getSize())
                    .filePath(path.toString())
                    .createdAt(LocalDateTime.now())
                    .build();

            return fileInfoRepository.save(info);

        } catch (Exception e) {
            throw new FileStorageException(ErrorCode.INTERNAL_ERROR, "", e);
        }

    }


    public FileInfo saveVenueImg(MultipartFile file) {

        if (file.isEmpty()) return null;

        try {
            String original = file.getOriginalFilename();
            String cleanName = StringUtils.cleanPath(original);
            String storedName = generateStoredName(cleanName);

            String fullDir = basePath + "/" + venusPath;
            ensureDirectory(fullDir);

            Path path = Paths.get(fullDir + "/" + storedName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            FileInfo info = FileInfo.builder()
                    .originalName(cleanName)
                    .storedName(storedName)
                    .contentType(file.getContentType())
                    .fileSize(file.getSize())
                    .filePath(path.toString())
                    .createdAt(LocalDateTime.now())
                    .build();

            return fileInfoRepository.save(info);

        } catch (Exception e) {
            throw new FileStorageException(ErrorCode.INTERNAL_ERROR, "", e);
        }
    }

    public FileInfo saveReviewImg(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;

        validateFile(file);

        try {
            String original = file.getOriginalFilename();
            String cleanName = StringUtils.cleanPath(original);
            String storedName = generateStoredName(cleanName);

            String fullDir = basePath + "/" + reviewsPath;
            ensureDirectory(fullDir);

            Path path = Paths.get(fullDir + "/" + storedName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            FileInfo info = FileInfo.builder()
                    .originalName(cleanName)
                    .storedName(storedName)
                    .contentType(file.getContentType())
                    .fileSize(file.getSize())
                    .filePath(path.toString())
                    .createdAt(LocalDateTime.now())
                    .build();

            return fileInfoRepository.save(info);

        } catch (Exception e) {
            throw new FileStorageException(ErrorCode.INTERNAL_ERROR, "" ,e);
        }
    }

    @Transactional
    public void deleteFile(FileInfo info) {
        try {
            Path path = Paths.get(info.getFilePath());
            Files.delete(path);
        } catch (Exception e) {
            throw new FileStorageException(ErrorCode.INTERNAL_ERROR, "", e);
        }
        fileInfoRepository.delete(info);
    }
}
