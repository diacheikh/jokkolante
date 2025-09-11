package com.cdcdevtools.jookolante.service;

import com.cdcdevtools.jookolante.domain.entity.User;
import com.cdcdevtools.jookolante.repository.UserRepository;
import com.cdcdevtools.jookolante.web.dto.UserDTO;
import com.cdcdevtools.jookolante.web.dto.UserResponseDTO;
import com.cdcdevtools.jookolante.web.mapper.UserMapper;
import com.cdcdevtools.jookolante.exception.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    //private final PasswordEncoder passwordEncoder; // Ajoutez plus tard pour JWT
    
    public UserResponseDTO createUser(UserDTO userDTO) {
        // Validation des doublons
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new DuplicateResourceException("Utilisateur", "email", userDTO.getEmail());
        }
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new DuplicateResourceException("Utilisateur", "username", userDTO.getUsername());
        }
        
        User user = userMapper.toEntity(userDTO);
        // Encoder le password (à compléter quand vous ajouterez la sécurité)
        user.setPassword("encoded_password_temp"); // Temporaire
        
        User savedUser = userRepository.save(user);
        return userMapper.toResponseDTO(savedUser);
    }
    
    public UserResponseDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", id));
        
        // Vérifier les doublons d'email (pour un autre utilisateur)
        if (!existingUser.getEmail().equals(userDTO.getEmail()) &&
            userRepository.existsByEmailAndIdNot(userDTO.getEmail(), id)) {
            throw new DuplicateResourceException("Utilisateur", "email", userDTO.getEmail());
        }
        
        // Vérifier les doublons de username (pour un autre utilisateur)
        if (!existingUser.getUsername().equals(userDTO.getUsername()) &&
            userRepository.existsByUsernameAndIdNot(userDTO.getUsername(), id)) {
            throw new DuplicateResourceException("Utilisateur", "username", userDTO.getUsername());
        }
        
        // Mettre à jour l'utilisateur
        userMapper.updateUserFromDTO(userDTO, existingUser);
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
    
    // Méthodes existantes...
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