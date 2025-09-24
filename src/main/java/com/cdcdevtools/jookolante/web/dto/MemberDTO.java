package com.cdcdevtools.jookolante.web.dto;
import com.cdcdevtools.jookolante.domain.enums.MemberType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private MemberType type;
    private Boolean isVoter;
    private Long associationId;
    private Long zoneId;
    private Long nin;
    // Champs électeur conditionnels
    private Long numberElecteur;
    private String votingPlace;
    private Integer votingBureau;
    private LocalDate registrationDate;
}