package org.example.exhibitiontimeslotbooking.entity.file;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exhibitiontimeslotbooking.entity.review.Review;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "review_files")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "review_id", nullable = true, foreignKey = @ForeignKey(name = "fk_review_files_reviews"))
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "file_id", nullable = false, foreignKey = @ForeignKey(name = "fk_review_files_file_info"))
    private FileInfo fileInfo;

    private Integer displayOrder;

    @Builder
    public ReviewFile (FileInfo fileInfo, Integer displayOrder){
        this.fileInfo = fileInfo;
        this.displayOrder = displayOrder;
    }

    public void setReview(Review review) {
        this.review = review;
        if (!review.getReviewFiles().contains(this)) {
            review.getReviewFiles().add(this);
        }
    }

    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
        if (!fileInfo.getReviewFiles().contains(this)) {
            fileInfo.getReviewFiles().add(this);
        }
    }

}
