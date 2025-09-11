package com.cdcdevtools.jookolante.web.dto;

import com.cdcdevtools.jookolante.domain.enums.ZoneLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class ZoneDTO {
    private Long id;
    private String name;
    private ZoneLevel level;
    private String code;
    private Long parentZoneId;
}