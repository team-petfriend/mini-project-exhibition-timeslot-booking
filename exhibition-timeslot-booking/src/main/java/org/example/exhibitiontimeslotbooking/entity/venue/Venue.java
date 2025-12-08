package org.example.exhibitiontimeslotbooking.entity.venue;

import jakarta.persistence.*;
import lombok.*;
import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;
import org.example.exhibitiontimeslotbooking.entity.base.BaseTimeEntity;
import org.example.exhibitiontimeslotbooking.entity.file.FileInfo;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "venues")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Venue extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 120)
    private String name;

    @Column(name = "address", length = 255)
    private String address;

    @Column(precision = 11, scale = 8)
    private BigDecimal latitude;

    @Column(precision = 12, scale = 8)
    private BigDecimal longitude;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    private Set<Exhibition> exhibitions = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "file_id", foreignKey = @ForeignKey(name = "fk_venues_file"))
    private FileInfo fileInfo;

    @Builder
    private Venue(String name, String address, BigDecimal latitude, BigDecimal longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void changedFile(FileInfo newFile) {
        this.fileInfo = newFile;
    }

    public void changedVenue(String name, String address, BigDecimal latitude, BigDecimal longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

