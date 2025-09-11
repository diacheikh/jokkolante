package com.cdcdevtools.jookolante.web.controller;

import com.cdcdevtools.jookolante.domain.entity.Member;
import com.cdcdevtools.jookolante.service.MemberService;
import com.cdcdevtools.jookolante.web.dto.MemberDTO;
import com.cdcdevtools.jookolante.web.mapper.MemberMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;
    private final MemberMapper mapper;

    public MemberController(MemberService service, MemberMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<MemberDTO> getAll() {
        return service.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MemberDTO> create(@RequestBody MemberDTO dto) {
        Member member = mapper.toEntity(dto);
        Member saved = service.save(member);
        return ResponseEntity.ok(mapper.toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
public List<MemberDTO> searchByName(@RequestParam String name) {
    return service.findByLastNameContaining(name).stream()
            .map(mapper::toDto)
            .toList();
}
}