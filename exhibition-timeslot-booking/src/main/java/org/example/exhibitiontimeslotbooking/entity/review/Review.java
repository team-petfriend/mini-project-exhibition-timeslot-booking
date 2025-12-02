package org.example.exhibitiontimeslotbooking.entity.review;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;
import org.example.exhibitiontimeslotbooking.entity.file.ReviewFile;
import org.example.exhibitiontimeslotbooking.entity.user.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "reviews",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_review_once", columnNames = {"exhibition_id", "user_id"})
        },
        indexes = {
                @Index(name = "idx_review_exhibition", columnList = "exhibition_id, rating")
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "rating", nullable = false)
    @Min(1) @Max(5)
    private Integer rating;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exhibition_id", foreignKey = @ForeignKey(name = "fk_review_exhibition_id"))
    private Exhibition exhibition;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_review_user_id"))
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "review_file_id", foreignKey = @ForeignKey(name = "fk_review_review_file_id"))
    private FileInfo reviewFile;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewFile> reviewFiles = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME(6)")
    private LocalDateTime createdAt;

    @Builder
    public Review(User user, Exhibition exhibition, Integer rating, String content, FileInfo reviewFile) {
        this.exhibition = exhibition;
        this.user = user;
        this.rating = rating;
        this.content = content;
    }

    public void validateRating() {
        if (rating < 1 || rating > 5) {
            throw new RuntimeException("Rating은 1에서 5사이여야 합니다.");
        }
    }

    public void setRating(@Min(1) @Max(5) Integer rating) {
        if (rating == null || rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating은 1에서 5사이여야 합니다.");
        }
        this.rating = rating;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void addReviewFile(ReviewFile reviewFile) {
        reviewFile.setReview(this);
        reviewFiles.add(reviewFile);
    }
}
