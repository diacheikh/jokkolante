package com.cdcdevtools.jookolante.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.cdcdevtools.jookolante.domain.enums.UserRole;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String username;
    
    @Column(nullable = false, length = 1000)
    private String password;
    
    @Column(nullable = false, length = 100)
    private String email;
    
    @Column(length = 30)
    private String firstName;
    
    @Column(length = 40)
    private String lastName;
    
    @Column(length = 20)
    private String phone;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private UserRole role;
    
    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone; // Zone géographique assignée
    
    @ManyToOne
    @JoinColumn(name = "association_id")
    private Association association; // Si responsable d'association
    
    @Column(name = "is_active")
    private Boolean active = true;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}