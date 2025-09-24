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

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false, unique = true)
    private Member member;

    @Column(name = "number_electeur", unique = true)
    private Long numberElecteur;

    @Column(name = "voting_place", length = 100)
    private String votingPlace;

    @Column(name = "voting_bureau")
    private Integer votingBureau;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id", nullable = false)
    private Zone zone; // ✅ Ajout de la zone électorale
}