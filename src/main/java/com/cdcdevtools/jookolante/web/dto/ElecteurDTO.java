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
    private Long nin;
    private Long electeurnumber;
    private String email;
    private String phone;
    private Long zoneId;
    private Integer votingBureau;
    private String votingPlace;
    private LocalDate registrationDate;
    private Boolean active;
}