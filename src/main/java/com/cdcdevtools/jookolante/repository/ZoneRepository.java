
package com.cdcdevtools.jookolante.repository;

import com.cdcdevtools.jookolante.domain.entity.Zone;
import com.cdcdevtools.jookolante.domain.enums.ZoneLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    List<Zone> findByLevel(ZoneLevel level);
    List<Zone> findByParentZoneId(Long parentId);
    List<Zone> findByNameContaining(String name);
    boolean existsByNameAndLevel(String name, ZoneLevel level);
}