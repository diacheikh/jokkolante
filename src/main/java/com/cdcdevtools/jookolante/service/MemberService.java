package com.cdcdevtools.jookolante.service;

import com.cdcdevtools.jookolante.domain.entity.Member;
import com.cdcdevtools.jookolante.domain.enums.MemberType;
import com.cdcdevtools.jookolante.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public List<Member> findAll() {
        return repository.findAll();
    }

    public Optional<Member> findById(Long id) {
        return repository.findById(id);
    }

    public Member save(Member member) {
        return repository.save(member);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    public List<Member> findByLastNameContaining(String name) {
    return repository.findByLastNameContainingIgnoreCase(name);
}

public List<Member> findByType(MemberType type) {
    return repository.findByType(type);
}
}