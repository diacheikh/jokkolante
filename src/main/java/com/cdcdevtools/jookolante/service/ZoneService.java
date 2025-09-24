package com.cdcdevtools.jookolante.service;

import com.cdcdevtools.jookolante.domain.entity.Zone;
import com.cdcdevtools.jookolante.domain.entity.User;
import com.cdcdevtools.jookolante.repository.ZoneRepository;
import com.cdcdevtools.jookolante.repository.UserRepository;
import com.cdcdevtools.jookolante.security.UserDetailsImpl;
import com.cdcdevtools.jookolante.web.mapper.ZoneMapper;
import com.cdcdevtools.jookolante.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneRepository zoneRepository;
    private final UserRepository userRepository;
    private final ZoneMapper zoneMapper;

    public List<Zone> findAll() {
        return zoneRepository.findAll();
    }

    public Optional<Zone> findById(Long id) {
        return zoneRepository.findById(id);
    }

    public Zone saveWithParent(Zone zone, Long parentId) {
        if (parentId != null) {
            Zone parent = zoneRepository.findById(parentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Zone", parentId));
            zone.setParentZone(parent);
        }
        return zoneRepository.save(zone);
    }

    public Zone saveWithResponsibility(Zone zone, Long responsibleId) {
        if (responsibleId != null) {
            User responsible = userRepository.findById(responsibleId)
                    .orElseThrow(() -> new ResourceNotFoundException("User", responsibleId));
            zone.setResponsible(responsible);
        }
        return zoneRepository.save(zone);
    }

    public Zone saveWithParentAndResponsibility(Zone zone, Long parentId, Long responsibleId) {
        if (parentId != null) {
            zone.setParentZone(zoneRepository.findById(parentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Zone", parentId)));
        }
        if (responsibleId != null) {
            zone.setResponsible(userRepository.findById(responsibleId)
                    .orElseThrow(() -> new ResourceNotFoundException("User", responsibleId)));
        }
        return zoneRepository.save(zone);
    }

    public void delete(Long id) {
        zoneRepository.deleteById(id);
    }
    public List<Zone> getZonesForCurrentUser() {
        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = currentUser.getUser();

        if (user.getRole().name().endsWith("_ADMIN") && user.getZone() != null) {
            return zoneRepository.findAllByParentZone(user.getZone());
        }
        return zoneRepository.findAll();
    }
}