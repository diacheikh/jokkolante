package com.cdcdevtools.jookolante.repository;

import com.cdcdevtools.jookolante.domain.entity.Member;
import com.cdcdevtools.jookolante.domain.entity.Zone;
import com.cdcdevtools.jookolante.domain.enums.MemberType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByNin(Long nin);
    //boolean existsByVoterNumber(Long voterNumber);

    List<Member> findByType(MemberType type);

    List<Member> findByAssociationId(Long associationId);

    List<Member> findByLastNameContainingIgnoreCase(String lastName);

    // 🔹 Filtrage direct par zone
    List<Member> findAllByZone(Zone zone);
}