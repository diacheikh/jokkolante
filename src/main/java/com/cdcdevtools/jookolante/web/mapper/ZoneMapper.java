package com.cdcdevtools.jookolante.web.mapper;

import com.cdcdevtools.jookolante.domain.entity.Zone;
import com.cdcdevtools.jookolante.web.dto.ZoneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ZoneMapper {

    @Mapping(source = "parentZone.id", target = "parentZoneId")
    ZoneDTO toDto(Zone zone);

    @Mapping(source = "parentZoneId", target = "parentZone.id")
    Zone toEntity(ZoneDTO dto);
}