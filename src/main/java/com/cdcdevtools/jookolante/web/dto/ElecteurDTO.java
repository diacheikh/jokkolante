package com.cdcdevtools.jookolante.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ElecteurDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private Long nin; // ✅ récupéré depuis Member

    private Long electeurnumber;
    private Long zoneId;
    private String communeName;
    private String arrondissementName;
    private String departmentName;
    private String regionName;

    private Integer votingBureau;
    private String votingPlace;
    private LocalDate registrationDate;
    private Boolean active;
}