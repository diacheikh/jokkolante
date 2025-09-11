package com.cdcdevtools.jookolante.service;

import com.cdcdevtools.jookolante.domain.entity.Association;
import com.cdcdevtools.jookolante.domain.entity.User;
import com.cdcdevtools.jookolante.domain.entity.Zone;
import com.cdcdevtools.jookolante.repository.AssociationRepository;
import com.cdcdevtools.jookolante.repository.UserRepository;
import com.cdcdevtools.jookolante.repository.ZoneRepository;
import com.cdcdevtools.jookolante.web.dto.AssociationDTO;
import com.cdcdevtools.jookolante.web.dto.AssociationResponseDTO;
import com.cdcdevtools.jookolante.web.mapper.AssociationMapper;
import com.cdcdevtools.jookolante.exception.ResourceNotFoundException;
import com.cdcdevtools.jookolante.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssociationService {
    private final AssociationRepository associationRepository;
    private final UserRepository userRepository;
    private final ZoneRepository zoneRepository;
    private final AssociationMapper associationMapper;

    public AssociationResponseDTO createAssociation(AssociationDTO associationDTO) {
        // Vérifier les doublons
        if (associationRepository.existsByName(associationDTO.getName())) {
            throw new DuplicateResourceException("Association", "name", associationDTO.getName());
        }

        // Vérifier que la zone existe
        Zone zone = zoneRepository.findById(associationDTO.getZoneId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone", associationDTO.getZoneId()));

        // Vérifier que le manager existe (si spécifié)
        User manager = null;
        if (associationDTO.getManagerId() != null) {
            manager = userRepository.findById(associationDTO.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", associationDTO.getManagerId()));
        }

        Association association = associationMapper.toEntity(associationDTO);
        association.setZone(zone);
        association.setManager(manager);

        Association savedAssociation = associationRepository.save(association);
        return associationMapper.toResponseDTO(savedAssociation);
    }

    public AssociationResponseDTO getAssociationById(Long id) {
        Association association = associationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Association", id));
        return associationMapper.toResponseDTO(association);
    }

    public List<AssociationResponseDTO> getAllAssociations() {
        return associationRepository.findAll().stream()
                .map(associationMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public AssociationResponseDTO updateAssociation(Long id, AssociationDTO associationDTO) {
        Association existingAssociation = associationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Association", id));

        // Vérifier les doublons de nom
        if (!existingAssociation.getName().equals(associationDTO.getName()) &&
            associationRepository.existsByNameAndIdNot(associationDTO.getName(), id)) {
            throw new DuplicateResourceException("Association", "name", associationDTO.getName());
        }

        // Vérifier et mettre à jour la zone
        if (!existingAssociation.getZone().getId().equals(associationDTO.getZoneId())) {
            Zone zone = zoneRepository.findById(associationDTO.getZoneId())
                    .orElseThrow(() -> new ResourceNotFoundException("Zone", associationDTO.getZoneId()));
            existingAssociation.setZone(zone);
        }

        // Vérifier et mettre à jour le manager
        if (associationDTO.getManagerId() != null && 
            (existingAssociation.getManager() == null || 
             !existingAssociation.getManager().getId().equals(associationDTO.getManagerId()))) {
            User manager = userRepository.findById(associationDTO.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", associationDTO.getManagerId()));
            existingAssociation.setManager(manager);
        }

        associationMapper.updateAssociationFromDTO(associationDTO, existingAssociation);
        Association updatedAssociation = associationRepository.save(existingAssociation);
        return associationMapper.toResponseDTO(updatedAssociation);
    }

    public void deleteAssociation(Long id) {
        if (!associationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Association", id);
        }
        associationRepository.deleteById(id);
    }

    public List<AssociationResponseDTO> getAssociationsByZoneId(Long zoneId) {
        return associationRepository.findByZoneId(zoneId).stream()
                .map(associationMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public AssociationResponseDTO getAssociationByManagerId(Long managerId) {
        Association association = associationRepository.findByManagerId(managerId)
                .orElseThrow(() -> new ResourceNotFoundException("Association", "managerId", managerId.toString()));
        return associationMapper.toResponseDTO(association);
    }
}