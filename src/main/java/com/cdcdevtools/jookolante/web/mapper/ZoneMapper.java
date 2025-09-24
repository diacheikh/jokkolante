package com.cdcdevtools.jookolante.web.mapper;

import com.cdcdevtools.jookolante.domain.entity.Zone;
import com.cdcdevtools.jookolante.web.dto.ZoneDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ZoneMapper {

    @Mapping(target = "childZones", ignore = true)
    @Mapping(target = "associations", ignore = true)
    @Mapping(target = "parentZone", ignore = true)
    @Mapping(target = "responsible", ignore = true)
    Zone toEntity(ZoneDTO dto);

    @Mapping(source = "parentZone.id", target = "parentId")
    @Mapping(source = "responsible.id", target = "responsibleId")
    ZoneDTO toDTO(Zone entity);

    @Mapping(target = "childZones", ignore = true)
    @Mapping(target = "associations", ignore = true)
    @Mapping(target = "parentZone", ignore = true)
    @Mapping(target = "responsible", ignore = true)
    void updateZoneFromDTO(ZoneDTO dto, @MappingTarget Zone entity);
}