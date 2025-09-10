package com.cdcdevtools.jookolante.web.mapper;

import com.cdcdevtools.jookolante.domain.entity.Association;
import com.cdcdevtools.jookolante.web.dto.AssociationDTO;
import com.cdcdevtools.jookolante.web.dto.AssociationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface AssociationMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "manager", ignore = true)
    @Mapping(target = "zone", ignore = true)
    @Mapping(target = "members", ignore = true)
    @Mapping(target = "events", ignore = true)
    Association toEntity(AssociationDTO associationDTO);
    
    AssociationResponseDTO toResponseDTO(Association association);
    
    AssociationDTO toDTO(Association association);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "manager", ignore = true)
    @Mapping(target = "zone", ignore = true)
    @Mapping(target = "members", ignore = true)
    @Mapping(target = "events", ignore = true)
    void updateAssociationFromDTO(AssociationDTO associationDTO, @MappingTarget Association association);
}