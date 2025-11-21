package org.example.exhibitiontimeslotbooking.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.slots.SLOTS_STATUS;

import java.time.LocalDateTime;

@Entity
@Table(name = "timeslots",
        uniqueConstraints = @UniqueConstraint(name = " uk_slot_unique", columnNames = {"exhibition_id", " start_time", "end_time"}),
        indexes = {
                @Index(name = "idx_slot_time ", columnList = "start_time, end_time")
        }
)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Timeslot {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slotId;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private int reserved = 0;

    @Column(nullable = false)
    private SLOTS_STATUS slotsStatus = SLOTS_STATUS.OPEN;


}
