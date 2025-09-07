package com.cdcdevtools.jookolante.service;

import com.cdcdevtools.jookolante.domain.entity.User;
import com.cdcdevtools.jookolante.repository.UserRepository;
import com.cdcdevtools.jookolante.web.dto.UserDTO;
import com.cdcdevtools.jookolante.web.dto.UserResponseDTO;
import com.cdcdevtools.jookolante.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    public UserResponseDTO createUser(UserDTO userDTO) {
        // Validation métier
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email déjà utilisé");
        }
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Nom d'utilisateur déjà utilisé");
        }
        
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toResponseDTO(savedUser);
    }
    
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<UserResponseDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toResponseDTO);
    }
    
   
    public UserResponseDTO updateUser(Long id, UserDTO userDTO) {
    User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    
    // Mise à jour des champs
    existingUser.setUsername(userDTO.getUsername());
    existingUser.setEmail(userDTO.getEmail());
    existingUser.setFirstName(userDTO.getFirstName());
    existingUser.setLastName(userDTO.getLastName());
   
    User updatedUser = userRepository.save(existingUser);
    return userMapper.toResponseDTO(updatedUser);
}

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Utilisateur non trouvé");
        }
        userRepository.deleteById(id);
    }
}