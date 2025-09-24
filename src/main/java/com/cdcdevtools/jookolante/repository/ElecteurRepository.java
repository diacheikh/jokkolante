package com.cdcdevtools.jookolante.repository;

import com.cdcdevtools.jookolante.domain.entity.Electeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElecteurRepository extends JpaRepository<Electeur, Long> {
    boolean existsByNumberElecteur(Long numberElecteur);
}