package com.cdcdevtools.jookolante.web.mapper;

import com.cdcdevtools.jookolante.domain.entity.User;
import com.cdcdevtools.jookolante.web.dto.UserDTO;
import com.cdcdevtools.jookolante.web.dto.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // DTO → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "zone", ignore = true) // zone sera géré dans le service
    @Mapping(target = "association", ignore = true) // association sera géré dans le service
    @Mapping(target = "password", ignore = true) // password géré séparément
    User toEntity(UserDTO userDTO);

    // Entity → ResponseDTO
    @Mapping(target = "zoneId", source = "zone.id")
    @Mapping(target = "associationId", source = "association.id")
    UserResponseDTO toResponseDTO(User user);

    // Entity → DTO
    @Mapping(target = "zoneId", source = "zone.id")
    @Mapping(target = "associationId", source = "association.id")
    UserDTO toDTO(User user);

    // Update entity from DTO
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "zone", ignore = true)
    @Mapping(target = "association", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateUserFromDTO(UserDTO userDTO, @MappingTarget User user);

    // Méthode spécifique pour le mapping du password (si besoin)
    default User toEntityWithPassword(UserDTO userDTO, String encodedPassword) {
        User user = toEntity(userDTO);
        user.setPassword(encodedPassword);
        return user;
    }
}