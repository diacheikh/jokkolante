package com.cdcdevtools.jookolante.web.mapper;

import com.cdcdevtools.jookolante.domain.entity.Electeur;
import com.cdcdevtools.jookolante.web.dto.ElecteurDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ElecteurMapper {

    @Mappings({
            @Mapping(source = "member.id", target = "id"),
            @Mapping(source = "member.lastName", target = "lastName"),
            @Mapping(source = "member.firstName", target = "firstName"),
            @Mapping(source = "member.email", target = "email"),
            @Mapping(source = "member.phone", target = "phone"),
            @Mapping(source = "member.nin", target = "nin"), // ✅ récupéré depuis Member
            @Mapping(source = "member.isVoter", target = "active"),
            @Mapping(source = "numberElecteur", target = "electeurnumber"),
            @Mapping(source = "votingPlace", target = "votingPlace"),
            @Mapping(source = "votingBureau", target = "votingBureau"),
            @Mapping(source = "registrationDate", target = "registrationDate"),
            @Mapping(source = "zone.id", target = "zoneId"),
            @Mapping(source = "zone.name", target = "communeName"),
            @Mapping(source = "zone.parentZone.name", target = "arrondissementName"),
            @Mapping(source = "zone.parentZone.parentZone.name", target = "departmentName"),
            @Mapping(source = "zone.parentZone.parentZone.parentZone.name", target = "regionName")
    })
    ElecteurDTO toDto(Electeur electeur);

    @Mappings({
            @Mapping(source = "electeurnumber", target = "numberElecteur"),
            @Mapping(source = "votingPlace", target = "votingPlace"),
            @Mapping(source = "votingBureau", target = "votingBureau"),
            @Mapping(source = "registrationDate", target = "registrationDate"),
            @Mapping(target = "member", ignore = true),
            @Mapping(target = "zone", ignore = true)
    })
    Electeur toEntity(ElecteurDTO dto);
}