package org.example.exhibitiontimeslotbooking.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.exhibitiontimeslotbooking.base.BaseTimeEntity;

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
    private Long venueId;

    @Column(name = "name", nullable = false, length = 120)
    private String name;

    @Column(name = "address", length = 255)
    private String address;

    @Column(precision = 11, scale = 8)
    private BigDecimal latitude;

    @Column(precision = 12, scale = 8)
    private BigDecimal longitude;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    private Set<Exhibition> exhibition = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "venue_file_id", foreignKey = @ForeignKey(name = "fk_venues_file"))
    private VenueFile venueFile;

    @Builder
    private Venue(String name, String address, BigDecimal latitude, BigDecimal longitude, Exhibition exhibition , VenueFile venueFile) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.exhibition = exhibition;
        this.venueFile = venueFile;
    }

    private void changedFile(VenueFile newFile) {
        this.venueFile = newFile;
    }

}

