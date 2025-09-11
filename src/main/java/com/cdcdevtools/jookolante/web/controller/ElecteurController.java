package com.cdcdevtools.jookolante.web.controller;

import com.cdcdevtools.jookolante.domain.entity.Electeur;
import com.cdcdevtools.jookolante.service.ElecteurService;
import com.cdcdevtools.jookolante.web.dto.ElecteurDTO;
import com.cdcdevtools.jookolante.web.mapper.ElecteurMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/electeurs")
public class ElecteurController {

    private final ElecteurService service;
    private final ElecteurMapper mapper;

    public ElecteurController(ElecteurService service, ElecteurMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ElecteurDTO> getAll() {
        return service.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElecteurDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ElecteurDTO> create(@RequestBody ElecteurDTO dto) {
        Electeur electeur = mapper.toEntity(dto);
        Electeur saved = service.save(electeur, dto.getZoneId());
        return ResponseEntity.ok(mapper.toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}