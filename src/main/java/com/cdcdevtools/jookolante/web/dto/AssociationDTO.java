package com.cdcdevtools.jookolante.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class AssociationDTO {
    private Long id;
    
    @NotBlank(message = "Le nom de l'association est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String name;
    
    @Size(max = 200, message = "La description ne peut pas dépasser 200 caractères")
    private String description;
    
    @Size(max = 100, message = "L'adresse ne peut pas dépasser 100 caractères")
    private String address;
    
    @jakarta.validation.constraints.Email(message = "L'email de contact doit être valide")
    private String contactEmail;
    
    @Size(max = 20, message = "Le téléphone ne peut pas dépasser 20 caractères")
    private String contactPhone;
    
    private Long zoneId;
    private Long managerId;
    private Boolean active;
}