package com.cdcdevtools.jookolante.service;

import com.cdcdevtools.jookolante.domain.entity.Zone;
import com.cdcdevtools.jookolante.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZoneService {

    private final ZoneRepository repository;

    public ZoneService(ZoneRepository repository) {
        this.repository = repository;
    }

    public List<Zone> findAll() {
        return repository.findAll();
    }

    public Optional<Zone> findById(Long id) {
        return repository.findById(id);
    }

    public Zone save(Zone zone) {
        return repository.save(zone);
    }

    public Zone saveWithParent(Zone zone, Long parentZoneId) {
        if (parentZoneId != null) {
            Zone parent = repository.findById(parentZoneId)
                .orElseThrow(() -> new RuntimeException("Parent zone not found"));
            zone.setParentZone(parent);
        }
        return repository.save(zone);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}