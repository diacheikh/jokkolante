package com.cdcdevtools.jookolante.web.controller;

import com.cdcdevtools.jookolante.domain.entity.Zone;
import com.cdcdevtools.jookolante.service.ZoneService;
import com.cdcdevtools.jookolante.web.dto.ZoneDTO;
import com.cdcdevtools.jookolante.web.mapper.ZoneMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService service;
    private final ZoneMapper mapper;

    /**
     * Liste complète des zones (ADMIN_GENERAL uniquement)
     */
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN_GENERAL')")
    public List<ZoneDTO> getAllZones() {
        return service.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    /**
     * Liste filtrée selon la zone de l’utilisateur connecté
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN_GENERAL', 'SOUS_ADMIN', 'VILLAGE_ADMIN')")
    public List<ZoneDTO> getZonesForCurrentUser() {
        return service.getZonesForCurrentUser().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZoneDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN_GENERAL', 'SOUS_ADMIN')")
    public ResponseEntity<ZoneDTO> create(@Valid @RequestBody ZoneDTO dto) {
        Zone zone = mapper.toEntity(dto);
        Zone saved = service.saveWithParentAndResponsibility(zone, dto.getParentId(), dto.getResponsibleId());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(saved));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN_GENERAL')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}