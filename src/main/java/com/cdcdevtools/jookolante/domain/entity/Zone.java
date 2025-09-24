package com.cdcdevtools.jookolante.domain.entity;

import com.cdcdevtools.jookolante.domain.enums.ZoneLevel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "zones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ZoneLevel level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_zone_id", nullable = true)
    private Zone parentZone;

    @OneToMany(mappedBy = "parentZone", cascade = CascadeType.ALL)
    private List<Zone> childZones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_id", nullable = true) // ✅ Nouveau champ
    private User responsible;

    @OneToMany(mappedBy = "zone")
    private List<Association> associations;
}