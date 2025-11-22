package org.example.exhibitiontimeslotbooking.service.impl.file;

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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl {

    @Value("${file.upload.base-path}")
    private String basePath;

    @Value("${file.upload.venues-img}")
    private String venusPath;

    private FileInfoRepository fileInfoRepository;

    private void ensureDirectory(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    private String generateStoredName(String originalName) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid + "_" + originalName;
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
