package org.example.exhibitiontimeslotbooking.entity;


import jakarta.persistence.*;
import jakarta.websocket.OnOpen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exhibitiontimeslotbooking.base.BaseTimeEntity;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.CAPACITY_POLICY;
import org.example.exhibitiontimeslotbooking.common.enums.exhibitions.STATUS;

import java.time.LocalDateTime;

@Entity
@Table(name = "exhibitions",
      indexes = {
        @Index(name = "idx_exhibitions_venue", columnList = "venue_id, status")
      }
)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Exhibition extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long exhibitionId;

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
    private CAPACITY_POLICY capacityPolicy = CAPACITY_POLICY.PER_DAY;

    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "venue_id", foreignKey = @ForeignKey(name = "fk_exhibition_venue"))
    private Venue venue;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "exhibition_id", foreignKey = @ForeignKey(name = "fk_exhibition_timeslot"))
    private Timeslot timeslot;


}
