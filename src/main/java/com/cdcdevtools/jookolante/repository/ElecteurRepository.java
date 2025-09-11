package com.cdcdevtools.jookolante.repository;

import com.cdcdevtools.jookolante.domain.entity.Electeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElecteurRepository extends JpaRepository<Electeur, Long> {
}