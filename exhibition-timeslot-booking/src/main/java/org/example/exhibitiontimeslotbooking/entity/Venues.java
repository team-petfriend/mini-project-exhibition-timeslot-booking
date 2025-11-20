package org.example.exhibitiontimeslotbooking.entity;


import jakarta.persistence.*;
import lombok.*;
import org.example.exhibitiontimeslotbooking.base.BaseTimeEntity;

import java.math.BigDecimal;


@Entity
@Table(name = "venues")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Venues extends BaseTimeEntity {

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


}

