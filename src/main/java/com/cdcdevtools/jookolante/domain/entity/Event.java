package com.cdcdevtools.jookolante.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "title", nullable = false, length = 100)
    private String title;
    
    @Column(name = "description", length = 500)
    private String description;
    
    @Column(name = "event_date")
    private LocalDateTime eventDate;
    
    @Column(name = "location", length = 100)
    private String location;
    
    @ManyToOne
    @JoinColumn(name = "association_id")
    private Association association;
    
    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;
    
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}