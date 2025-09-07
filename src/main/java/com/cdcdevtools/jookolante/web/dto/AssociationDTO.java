package com.cdcdevtools.jookolante.web.dto;

import lombok.Data;

@Data
public class AssociationDTO {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String contactEmail;
    private String contactPhone;
    private Long zoneId;
    private Long managerId;
    private Boolean active;
}
