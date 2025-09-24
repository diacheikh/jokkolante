package com.cdcdevtools.jookolante.repository;

import com.cdcdevtools.jookolante.domain.entity.Association;
import com.cdcdevtools.jookolante.domain.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AssociationRepository extends JpaRepository<Association, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
    List<Association> findByZoneId(Long zoneId);
    Optional<Association> findByManagerId(Long managerId);
    List<Association> findByActiveTrue();
    List<Association> findByActiveFalse();

    List<Association> findAllByZone(Zone userZone);
}