package com.cdcdevtools.jookolante.web.controller;

import com.cdcdevtools.jookolante.domain.entity.Member;
import com.cdcdevtools.jookolante.service.MemberService;
import com.cdcdevtools.jookolante.web.dto.MemberDTO;
import com.cdcdevtools.jookolante.web.mapper.MemberMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;
    private final MemberMapper mapper;

    /**
     * Liste complète des membres (ADMIN_GENERAL uniquement)
     */
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN_GENERAL')")
    public List<MemberDTO> getAllMembers() {
        return service.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    /**
     * Liste filtrée selon la zone de l’utilisateur connecté
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN_GENERAL', 'SOUS_ADMIN', 'VILLAGE_ADMIN')")
    public List<MemberDTO> getMembersForCurrentUser() {
        return service.getMembersForCurrentUser().stream()
                .map(mapper::toDto)
                .toList();
    }

    /**
     * Récupération d’un membre par son ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Création d’un membre (avec électeur si applicable)
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN_GENERAL', 'SOUS_ADMIN', 'VILLAGE_ADMIN')")
    public ResponseEntity<MemberDTO> create(@Valid @RequestBody MemberDTO dto) {
        service.createMember(dto); // méthode qui gère aussi l’électeur
        Member created = service.findByLastNameContaining(dto.getLastName()).stream()
                .filter(m -> m.getFirstName().equals(dto.getFirstName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Membre non retrouvé après création"));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(created));
    }

    /**
     * Suppression d’un membre
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN_GENERAL')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Recherche par nom
     */
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN_GENERAL', 'SOUS_ADMIN', 'VILLAGE_ADMIN')")
    public List<MemberDTO> searchByName(@RequestParam String name) {
        return service.findByLastNameContaining(name).stream()
                .map(mapper::toDto)
                .toList();
    }
}