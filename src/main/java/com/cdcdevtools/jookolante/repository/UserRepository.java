// UserRepository.java
package com.cdcdevtools.jookolante.repository;

import com.cdcdevtools.jookolante.domain.entity.User;
import com.cdcdevtools.jookolante.domain.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    List<User> findByRole(UserRole role);
    List<User> findByAssociationId(Long associationId);
    List<User> findByZoneId(Long zoneId);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}