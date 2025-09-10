package com.cdcdevtools.jookolante.web.dto;

// UserResponseDTO.java (pour les réponses sans mot de passe)
import com.cdcdevtools.jookolante.domain.enums.UserRole;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private UserRole role;
    private Long zoneId;
    private Long associationId;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}