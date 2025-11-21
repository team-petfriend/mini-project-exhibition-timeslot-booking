package org.example.exhibitiontimeslotbooking.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.exhibitiontimeslotbooking.base.BaseTimeEntity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "venue_file")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VenueFile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(nullable = false, length = 255)
    private String originalName;

    @Column(nullable = false, length = 255)
    private String storedName;

    @Column(length = 255)
    private String contentType;

    private Long fileSize;

    @Column(nullable = false, length = 255)
    private String filePath;

}
