package com.cdcdevtools.jookolante.service;

import com.cdcdevtools.jookolante.domain.entity.User;
import com.cdcdevtools.jookolante.domain.entity.Zone;
import com.cdcdevtools.jookolante.domain.entity.Association;
import com.cdcdevtools.jookolante.repository.UserRepository;
import com.cdcdevtools.jookolante.repository.ZoneRepository;
import com.cdcdevtools.jookolante.repository.AssociationRepository;
import com.cdcdevtools.jookolante.web.dto.UserDTO;
import com.cdcdevtools.jookolante.web.dto.UserResponseDTO;
import com.cdcdevtools.jookolante.web.mapper.UserMapper;
import com.cdcdevtools.jookolante.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ZoneRepository zoneRepository;
    private final AssociationRepository associationRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new DuplicateResourceException("Utilisateur", "email", userDTO.getEmail());
        }
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new DuplicateResourceException("Utilisateur", "username", userDTO.getUsername());
        }

        Zone zone = zoneRepository.findById(userDTO.getZoneId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone", userDTO.getZoneId()));
        Association association = associationRepository.findById(userDTO.getAssociationId())
                .orElseThrow(() -> new ResourceNotFoundException("Association", userDTO.getAssociationId()));

        User user = userMapper.toEntity(userDTO);
        user.setZone(zone);
        user.setAssociation(association);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setActive(true);

        User savedUser = userRepository.save(user);
        return userMapper.toResponseDTO(savedUser);
    }

    public UserResponseDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", id));

        if (!existingUser.getEmail().equals(userDTO.getEmail()) &&
                userRepository.existsByEmailAndIdNot(userDTO.getEmail(), id)) {
            throw new DuplicateResourceException("Utilisateur", "email", userDTO.getEmail());
        }

        if (!existingUser.getUsername().equals(userDTO.getUsername()) &&
                userRepository.existsByUsernameAndIdNot(userDTO.getUsername(), id)) {
            throw new DuplicateResourceException("Utilisateur", "username", userDTO.getUsername());
        }

        userMapper.updateUserFromDTO(userDTO, existingUser);

        if (userDTO.getPassword() != null && !userDTO.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toResponseDTO(updatedUser);
    }

    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "email", email));
        return userMapper.toResponseDTO(user);
    }

    public UserResponseDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "username", username));
        return userMapper.toResponseDTO(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", id));
        return userMapper.toResponseDTO(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Utilisateur", id);
        }
        userRepository.deleteById(id);
    }
}