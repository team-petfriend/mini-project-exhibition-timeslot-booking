package org.example.exhibitiontimeslotbooking.entity.exhibition;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.CapacityPolicy;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.ExhibitionStatus;
import org.example.exhibitiontimeslotbooking.entity.file.ExhibitionFile;
import org.example.exhibitiontimeslotbooking.entity.timeslot.Timeslot;
import org.example.exhibitiontimeslotbooking.entity.base.BaseTimeEntity;
import org.example.exhibitiontimeslotbooking.entity.venue.Venue;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "exhibitions",
      indexes = {
        @Index(name = "idx_exhibitions_venue", columnList = "venue_id, status")
      }
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Exhibition extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private ExhibitionStatus exhibitionStatus = ExhibitionStatus.SCHEDULED;

    @Column(nullable = false)
    private CapacityPolicy capacityPolicy = CapacityPolicy.PER_DAY;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "venue_id", foreignKey = @ForeignKey(name = "fk_exhibition_venue"))
    private Venue venue;

    @OneToMany(mappedBy = "exhibition")
    private Set<Timeslot> timeslots = new HashSet<>();

    @OneToMany(mappedBy = "exhibition")
    private Set<ExhibitionFile> exhibitionFiles = new HashSet<>();

    @Builder
    public Exhibition(
            String title, String description, LocalDateTime startDate, LocalDateTime endDate, ExhibitionStatus exhibitionStatus, CapacityPolicy capacityPolicy, Venue venue
    ) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.exhibitionStatus = (exhibitionStatus != null) ? exhibitionStatus : ExhibitionStatus.SCHEDULED;
        this.capacityPolicy = (capacityPolicy != null) ? capacityPolicy : CapacityPolicy.PER_DAY ;
        this.venue = venue;
        this.exhibitionFiles = new HashSet<>();
        this.timeslots = new HashSet<>();
    }

    public void updated (String title, String description, LocalDateTime startDate, LocalDateTime endDate, CapacityPolicy capacityPolicy) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacityPolicy = capacityPolicy;
    }

    public void changedStatus (ExhibitionStatus newExhibitionStatus) {
        this.exhibitionStatus = newExhibitionStatus;
    }

}
