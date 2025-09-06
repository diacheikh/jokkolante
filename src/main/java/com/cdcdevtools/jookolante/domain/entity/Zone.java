package com.cdcdevtools.jookolante.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import com.cdcdevtools.jookolante.domain.enums.ZoneLevel;

import java.util.List;

@Entity
@Table(name = "zones")
@Getter
@Setter
@NoArgsConstructor
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false, length = 20)
    private ZoneLevel level;
    
    @Column(name = "code", length = 10)
    private String code;
    
    @ManyToOne
    @JoinColumn(name = "parent_zone_id")
    private Zone parentZone;
    
    @OneToMany(mappedBy = "parentZone")
    private List<Zone> childZones;
    
    @OneToMany(mappedBy = "zone")
    private List<Association> associations;
    
    @OneToMany(mappedBy = "zone")
    private List<User> administrators;
}