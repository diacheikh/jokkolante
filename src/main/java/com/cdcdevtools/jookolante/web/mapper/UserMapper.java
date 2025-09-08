package com.cdcdevtools.jookolante.web.mapper;

import com.cdcdevtools.jookolante.domain.entity.User;
import com.cdcdevtools.jookolante.web.dto.UserDTO;
import com.cdcdevtools.jookolante.web.dto.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "password", ignore = true) // On gère le password séparément
    User toEntity(UserDTO userDTO);
    
    UserResponseDTO toResponseDTO(User user);
    
    UserDTO toDTO(User user);
    
    // Méthode pour mettre à jour un User existant à partir d'un DTO
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateUserFromDTO(UserDTO userDTO, @MappingTarget User user);
    
    // Méthode spécifique pour le mapping du password (si besoin)
    default User toEntityWithPassword(UserDTO userDTO, String encodedPassword) {
        User user = toEntity(userDTO);
        user.setPassword(encodedPassword);
        return user;
    }
}