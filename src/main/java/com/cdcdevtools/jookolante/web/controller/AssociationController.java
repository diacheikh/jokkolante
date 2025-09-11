package com.cdcdevtools.jookolante.web.controller;

import com.cdcdevtools.jookolante.web.dto.AssociationDTO;
import com.cdcdevtools.jookolante.web.dto.AssociationResponseDTO;
import com.cdcdevtools.jookolante.service.AssociationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/associations")
@RequiredArgsConstructor
public class AssociationController {
    private final AssociationService associationService;

    @PostMapping
    public ResponseEntity<AssociationResponseDTO> createAssociation(
            @Valid @RequestBody AssociationDTO associationDTO) {
        AssociationResponseDTO createdAssociation = associationService.createAssociation(associationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAssociation);
    }

    @GetMapping
    public ResponseEntity<List<AssociationResponseDTO>> getAllAssociations() {
        List<AssociationResponseDTO> associations = associationService.getAllAssociations();
        return ResponseEntity.ok(associations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociationResponseDTO> getAssociationById(@PathVariable Long id) {
        AssociationResponseDTO association = associationService.getAssociationById(id);
        return ResponseEntity.ok(association);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationResponseDTO> updateAssociation(
            @PathVariable Long id,
            @Valid @RequestBody AssociationDTO associationDTO) {
        AssociationResponseDTO updatedAssociation = associationService.updateAssociation(id, associationDTO);
        return ResponseEntity.ok(updatedAssociation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssociation(@PathVariable Long id) {
        associationService.deleteAssociation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/zone/{zoneId}")
    public ResponseEntity<List<AssociationResponseDTO>> getAssociationsByZone(@PathVariable Long zoneId) {
        List<AssociationResponseDTO> associations = associationService.getAssociationsByZoneId(zoneId);
        return ResponseEntity.ok(associations);
    }

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<AssociationResponseDTO> getAssociationByManager(@PathVariable Long managerId) {
        AssociationResponseDTO association = associationService.getAssociationByManagerId(managerId);
        return ResponseEntity.ok(association);
    }
}