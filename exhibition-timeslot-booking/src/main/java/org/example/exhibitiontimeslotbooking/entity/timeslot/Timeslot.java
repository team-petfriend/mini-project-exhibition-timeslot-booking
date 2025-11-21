package org.example.exhibitiontimeslotbooking.entity.timeslot;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.exhibitiontimeslotbooking.common.enums.slots.SLOTSSTATUS;
import org.example.exhibitiontimeslotbooking.entity.base.BaseTimeEntity;
import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;

import java.time.LocalDateTime;

@Entity
@Table(name = "timeslots",
        uniqueConstraints = @UniqueConstraint(name = "uk_slot_unique", columnNames = {"exhibition_id", "start_time", "end_time"}),
        indexes = {
                @Index(name= "idx_slot_time", columnList = "start_time, end_time")
        }
)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Timeslot extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private int reserved = 0;

    @Column(nullable = false)
    private SLOTSSTATUS slotsStatus = SLOTSSTATUS.OPEN;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exhibition_id", nullable = false, foreignKey = @ForeignKey(name = "fk_timeslot_exhibition"))
    private Exhibition exhibition;

    @Builder
    public Timeslot(LocalDateTime startTime, LocalDateTime endTime, int capacity, int reserved, SLOTSSTATUS slotsStatus, Exhibition exhibition) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.reserved = reserved;
        this.slotsStatus = (slotsStatus != null) ? slotsStatus : SLOTSSTATUS.OPEN;
        this.exhibition = exhibition;
    }

    public void countReserved (int count) {
        if (this.capacity < reserved + count) {
            throw new IllegalArgumentException("남은 좌석을 다시 확인해주세요.");
        }
        this.reserved = count + reserved;
    }

    public int getRemainingCapacity() {
        return this.capacity - this.reserved;
    }

    public boolean checkedCapacity (int count) {
        return  this.reserved + count <= this.capacity;
    }

    public void checkStatus (SLOTSSTATUS newStatus) {
        this.slotsStatus = newStatus;
    }

}
