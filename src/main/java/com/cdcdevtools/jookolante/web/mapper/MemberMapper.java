package com.cdcdevtools.jookolante.web.mapper;

import com.cdcdevtools.jookolante.domain.entity.Member;
import com.cdcdevtools.jookolante.web.dto.MemberDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mappings({
            @Mapping(source = "association.id", target = "associationId"),
            @Mapping(source = "zone.id", target = "zoneId"),

            @Mapping(source = "electeur.numberElecteur", target = "numberElecteur"),
    // @Mapping(source = "electeur.nin", target = "nin"),
            @Mapping(source = "electeur.votingPlace", target = "votingPlace"),
            @Mapping(source = "electeur.votingBureau", target = "votingBureau"),
            @Mapping(source = "electeur.registrationDate", target = "registrationDate")
    })
    MemberDTO toDto(Member member);

    @Mappings({
            @Mapping(source = "associationId", target = "association.id"),
            @Mapping(source = "zoneId", target = "zone.id"),
            @Mapping(target = "electeur", ignore = true)
    })
    Member toEntity(MemberDTO dto);

}