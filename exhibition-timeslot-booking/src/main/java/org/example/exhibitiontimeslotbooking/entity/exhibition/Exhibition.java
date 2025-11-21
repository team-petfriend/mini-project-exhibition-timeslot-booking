package org.example.exhibitiontimeslotbooking.entity.exhibition;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.CAPACITYPOLICY;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.STATUS;
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
    private STATUS status = STATUS.SCHEDULED;

    @Column(nullable = false)
    private CAPACITYPOLICY capacityPolicy = CAPACITYPOLICY.PER_DAY;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "venue_id", foreignKey = @ForeignKey(name = "fk_exhibition_venue"))
    private Venue venue;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "exhibition_id", foreignKey = @ForeignKey(name = "fk_exhibition_timeslot"))
    private Set<Timeslot> timeslots;

    @OneToMany(mappedBy = "exhibition")
    private Set<ExhibitionFile> exhibitionFiles = new HashSet<>();

    @Builder
    public  Exhibition(
            String title, String description, LocalDateTime startDate, LocalDateTime endDate, STATUS status, CAPACITYPOLICY capacityPolicy, Venue venue
    ) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = (status != null) ? status : STATUS.SCHEDULED;
        this.capacityPolicy = (capacityPolicy != null) ? capacityPolicy : CAPACITYPOLICY.PER_DAY ;
        this.venue = venue;
        this.exhibitionFiles = new HashSet<>();
        this.timeslots = new HashSet<>();
    }

    public void updated (String title, String description, LocalDateTime startDate, LocalDateTime endDate, CAPACITYPOLICY capacityPolicy) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacityPolicy = capacityPolicy;
    }

    public void addSetTimeSlot (Timeslot timeslots) {
        timeslots.add
    }

    public void changedStatus (STATUS newStatus) {
        this.status = newStatus;
    }

}
