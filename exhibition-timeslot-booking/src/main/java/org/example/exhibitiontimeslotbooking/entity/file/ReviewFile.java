package org.example.exhibitiontimeslotbooking.entity.file;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exhibitiontimeslotbooking.entity.review.Review;

@Entity
@Table(name = "review_files")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "review_id", nullable = false, foreignKey = @ForeignKey(name = "fk_review_files_reviews"))
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "file_id", nullable = false, foreignKey = @ForeignKey(name = "fk_review_files_file_info"))
    private FileInfo fileInfo;

    private Integer displayOrder;

    @Builder
    public ReviewFile (Review review, FileInfo fileInfo, Integer displayOrder){
        this.review = review;
        this.fileInfo = fileInfo;
        this.displayOrder = displayOrder;
    }

}
