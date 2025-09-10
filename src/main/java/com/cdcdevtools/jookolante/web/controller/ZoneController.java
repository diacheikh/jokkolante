package com.cdcdevtools.jookolante.web.controller;

import com.cdcdevtools.jookolante.domain.entity.Zone;
import com.cdcdevtools.jookolante.service.ZoneService;
import com.cdcdevtools.jookolante.web.dto.ZoneDTO;
import com.cdcdevtools.jookolante.web.mapper.ZoneMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zones")
public class ZoneController {

    private final ZoneService service;
    private final ZoneMapper mapper;

    public ZoneController(ZoneService service, ZoneMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ZoneDTO> getAll() {
        return service.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZoneDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
public ResponseEntity<ZoneDTO> create(@RequestBody ZoneDTO dto) {
    Zone zone = mapper.toEntity(dto);
    Zone saved = service.saveWithParent(zone, dto.getParentZoneId());
    return ResponseEntity.ok(mapper.toDto(saved));
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}