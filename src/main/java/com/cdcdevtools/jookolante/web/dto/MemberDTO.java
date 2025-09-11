package com.cdcdevtools.jookolante.web.dto;

import com.cdcdevtools.jookolante.domain.enums.MemberType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private Long nin;
    private Long voterNumber;
    private String email;
    private String phone;
    private MemberType type;
    private Long associationId;
    private Boolean isVoter;
    private LocalDate membershipDate;
    private LocalDate membershipExpiration;
}