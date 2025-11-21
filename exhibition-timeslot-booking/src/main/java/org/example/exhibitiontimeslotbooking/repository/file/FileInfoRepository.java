package org.example.exhibitiontimeslotbooking.repository.file;

import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileInfo,Long> {
}
