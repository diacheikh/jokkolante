package com.cdcdevtools.jookolante.service;

import com.cdcdevtools.jookolante.domain.entity.*;
import com.cdcdevtools.jookolante.domain.enums.MemberType;
import com.cdcdevtools.jookolante.web.dto.MemberDTO;
import com.cdcdevtools.jookolante.repository.*;
import com.cdcdevtools.jookolante.security.UserDetailsImpl;
import com.cdcdevtools.jookolante.web.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;
    private final ElecteurRepository electeurRepository;
    private final AssociationRepository associationRepository;
    private final ZoneRepository zoneRepository;
    private final MemberMapper memberMapper;

    public void createMember(MemberDTO dto) {
        Member member = memberMapper.toEntity(dto);

        Association association = associationRepository.findById(dto.getAssociationId())
                .orElseThrow(() -> new IllegalArgumentException("Association introuvable"));
        Zone zone = zoneRepository.findById(dto.getZoneId())
                .orElseThrow(() -> new IllegalArgumentException("Zone introuvable"));

        member.setAssociation(association);
        member.setZone(zone);
        member.setIsVoter(dto.getIsVoter());

        memberRepository.save(member);

        if (Boolean.TRUE.equals(dto.getIsVoter())) {
            Electeur electeur = new Electeur();
            electeur.setMember(member);
            electeur.setNumberElecteur(dto.getNumberElecteur());
            //electeur.setNin(dto.getNin());
            electeur.setVotingPlace(dto.getVotingPlace());
            electeur.setVotingBureau(dto.getVotingBureau());
            electeur.setRegistrationDate(dto.getRegistrationDate());

            electeurRepository.save(electeur);
        }
    }


    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    public List<Member> findByLastNameContaining(String name) {
        return memberRepository.findByLastNameContainingIgnoreCase(name);
    }

    public List<Member> findByType(MemberType type) {
        return memberRepository.findByType(type);
    }

    /**
     * Retourne les membres visibles par l'utilisateur connecté :
     * - ADMIN_GENERAL → tous les membres
     * - SOUS_ADMIN / VILLAGE_ADMIN → membres de leur zone
     * - Sinon → liste vide
     */
    public List<Member> getMembersForCurrentUser() {
        UserDetailsImpl currentUser = (UserDetailsImpl) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        User user = currentUser.getUser();

        if ("ADMIN_GENERAL".equals(user.getRole().name())) {
            return memberRepository.findAll();
        }

        if (user.getZone() != null) {
            return memberRepository.findAllByZone(user.getZone());
        }

        return List.of();
    }
}