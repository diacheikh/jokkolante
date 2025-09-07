package com.cdcdevtools.jookolante.repository;

import com.cdcdevtools.jookolante.domain.entity.Association;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssociationRepository extends JpaRepository<Association, Long> {
    List<Association> findByZoneId(Long zoneId);
    List<Association> findByManagerId(Long managerId);
    boolean existsByName(String name);
}