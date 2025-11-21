package org.example.exhibitiontimeslotbooking.repository.file;

import org.example.exhibitiontimeslotbooking.entity.file.ReviewFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewFileRepository extends JpaRepository<ReviewFile, Long> {
}
