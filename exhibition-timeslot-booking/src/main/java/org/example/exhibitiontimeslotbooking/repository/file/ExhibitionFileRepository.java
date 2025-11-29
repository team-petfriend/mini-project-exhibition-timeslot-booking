package org.example.exhibitiontimeslotbooking.repository.file;

import org.apache.tomcat.jni.FileInfo;
import org.example.exhibitiontimeslotbooking.entity.file.ExhibitionFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExhibitionFileRepository extends JpaRepository<ExhibitionFile, Long> {
    Optional<ExhibitionFile> findByFileInfoId(Long fileId);

    List<ExhibitionFile> findByExhibitionIdOrderByDisplayOrderAsc(Long exhibitionId);
}
