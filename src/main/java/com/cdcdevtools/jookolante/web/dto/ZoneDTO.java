package com.cdcdevtools.jookolante.web.dto;

import com.cdcdevtools.jookolante.domain.enums.ZoneLevel;
import lombok.Data;

@Data
public class ZoneDTO {
    private Long id;
    private String name;
    private ZoneLevel level;
    private String code;
    private Long parentZoneId;
}