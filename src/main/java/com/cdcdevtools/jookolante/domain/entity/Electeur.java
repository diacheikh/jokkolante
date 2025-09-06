package com.cdcdevtools.jookolante.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "electeurs")
@Getter
@Setter
@NoArgsConstructor
public class Electeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;
    
    @Column(name = "first_name", nullable = false, length = 40)
    private String firstName;
    
    @Column(name = "nin", unique = true, precision = 13)
    private Long nin;
    
    @Column(name = "voter_number", unique = true, precision = 9)
    private Long voterNumber;
    
    @Column(name = "email", length = 100)
    private String email;
    
    @Column(name = "phone", length = 20)
    private String phone;
    
    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;
    
    @Column(name = "voting_bureau")
    private Integer votingBureau;
    
    @Column(name = "voting_place", length = 100)
    private String votingPlace;
    
    @Column(name = "registration_date")
    private LocalDate registrationDate;
    
    @Column(name = "is_active")
    private Boolean active = true;
}