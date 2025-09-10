package com.cdcdevtools.jookolante.web.mapper;

import com.cdcdevtools.jookolante.domain.entity.Member;
import com.cdcdevtools.jookolante.web.dto.MemberDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(source = "association.id", target = "associationId")
    MemberDTO toDto(Member member);

    @Mapping(source = "associationId", target = "association.id")
    Member toEntity(MemberDTO dto);
}