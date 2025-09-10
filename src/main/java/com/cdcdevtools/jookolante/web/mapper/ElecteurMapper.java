package com.cdcdevtools.jookolante.web.mapper;

import com.cdcdevtools.jookolante.domain.entity.Electeur;
import com.cdcdevtools.jookolante.web.dto.ElecteurDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ElecteurMapper {

    @Mapping(target = "zone", ignore = true) // géré dans le service
    Electeur toEntity(ElecteurDTO dto);

    @Mapping(source = "zone.id", target = "zoneId")
    ElecteurDTO toDto(Electeur electeur);

    @Mapping(target = "zone", ignore = true)
    void updateFromDto(ElecteurDTO dto, @MappingTarget Electeur electeur);
}