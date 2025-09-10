package com.cdcdevtools.jookolante.repository;

import com.cdcdevtools.jookolante.domain.entity.Member;
import com.cdcdevtools.jookolante.domain.enums.MemberType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // Vérifie l'existence par NIN ou numéro d'électeur
    boolean existsByNin(Long nin);
    boolean existsByVoterNumber(Long voterNumber);

    // Recherche par type de membre
    List<Member> findByType(MemberType type);

    // Recherche par association
    List<Member> findByAssociationId(Long associationId);

    // Recherche par nom (partiel)
    List<Member> findByLastNameContainingIgnoreCase(String lastName);
}