package com.cdcdevtools.jookolante.web.mapper;

import com.cdcdevtools.jookolante.domain.entity.Association;
import com.cdcdevtools.jookolante.web.dto.AssociationDTO;
import com.cdcdevtools.jookolante.web.dto.AssociationResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AssociationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "zone", ignore = true)
    @Mapping(target = "manager", ignore = true)
    @Mapping(target = "members", ignore = true)
   // @Mapping(target = "events", ignore = true)
    Association toEntity(AssociationDTO dto);

    @Mapping(source = "zone.id", target = "zoneId")
    @Mapping(source = "manager.id", target = "managerId")
    AssociationResponseDTO toResponseDTO(Association entity);

    @Mapping(source = "zone.id", target = "zoneId")
    @Mapping(source = "manager.id", target = "managerId")
    AssociationDTO toDTO(Association entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "zone", ignore = true)
    @Mapping(target = "manager", ignore = true)
    @Mapping(target = "members", ignore = true)
    //@Mapping(target = "events", ignore = true)
    void updateAssociationFromDTO(AssociationDTO dto, @MappingTarget Association entity);
}