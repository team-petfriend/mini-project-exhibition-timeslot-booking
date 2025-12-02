package org.example.exhibitiontimeslotbooking.entity.file;

import jakarta.persistence.*;
import lombok.*;
import org.example.exhibitiontimeslotbooking.entity.review.Review;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "file_infos")
public class FileInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private String storedName;

    private String contentType;
    private Long fileSize;

    @Column(nullable = false)
    private String filePath;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "reviewFile", fetch = FetchType.LAZY)
    private Review review;

    @OneToMany(mappedBy = "fileInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReviewFile> reviewFiles = new HashSet<>();

    public void addReviewFile(ReviewFile reviewFile) {
        reviewFiles.add(reviewFile);
        reviewFile.setFileInfo(this);
    }
}
