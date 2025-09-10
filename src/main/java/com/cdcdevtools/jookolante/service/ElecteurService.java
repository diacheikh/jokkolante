package com.cdcdevtools.jookolante.service;

import com.cdcdevtools.jookolante.domain.entity.Electeur;
import com.cdcdevtools.jookolante.repository.ElecteurRepository;
import com.cdcdevtools.jookolante.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElecteurService {

    private final ElecteurRepository repository;
    private final ZoneRepository zoneRepository;

    public ElecteurService(ElecteurRepository repository, ZoneRepository zoneRepository) {
        this.repository = repository;
        this.zoneRepository = zoneRepository;
    }

    public List<Electeur> findAll() {
        return repository.findAll();
    }

    public Optional<Electeur> findById(Long id) {
        return repository.findById(id);
    }

    public Electeur save(Electeur electeur, Long zoneId) {
        electeur.setZone(zoneRepository.findById(zoneId).orElseThrow());
        return repository.save(electeur);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}