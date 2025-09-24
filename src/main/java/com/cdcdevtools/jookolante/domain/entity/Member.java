package com.cdcdevtools.jookolante.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import com.cdcdevtools.jookolante.domain.enums.MemberType;

import java.time.LocalDate;

@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "first_name", nullable = false, length = 40)
    private String firstName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;


    @Column(name = "nin", unique = true)
    private Long nin;

    @Column(name = "is_voter", nullable = false)
    private Boolean isVoter = false;

    @Column(name = "membership_date")
    private LocalDate membershipDate;

    @Column(name = "membership_expiration")
    private LocalDate membershipExpiration;

    private MemberType type;

    @ManyToOne
    @JoinColumn(name = "association_id", nullable = false)
    private Association association;

    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)

    private Zone zone;


    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Electeur electeur;
}