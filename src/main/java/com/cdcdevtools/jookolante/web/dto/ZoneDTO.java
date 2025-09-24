package com.cdcdevtools.jookolante.web.dto;

import com.cdcdevtools.jookolante.domain.enums.ZoneLevel;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZoneDTO {
    private Long id;
    private String code;
    private String name;
    private ZoneLevel level;
    private Long parentId;
    private Long responsibleId; // ✅ Nouveau champ
}