package com.cdcdevtools.jookolante.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "associations")
@Getter
@Setter
@NoArgsConstructor
public class Association {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "description", length = 200)
    private String description;
    
    @Column(name = "address", length = 100)
    private String address;
    
    @Column(name = "contact_email", length = 50)
    private String contactEmail;
    
    @Column(name = "contact_phone", length = 20)
    private String contactPhone;
    
    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)
    private Zone zone;
    
    @OneToOne
    @JoinColumn(name = "manager_id")
    private User manager;
    
    @OneToMany(mappedBy = "association")
    private List<Member> members;
    
    @OneToMany(mappedBy = "association")
    private List<Event> events;
    
    @Column(name = "is_active")
    private Boolean active = true;
    
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}