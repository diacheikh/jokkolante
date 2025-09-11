package com.cdcdevtools.jookolante.web.controller;

import com.cdcdevtools.jookolante.web.dto.UserDTO;
import com.cdcdevtools.jookolante.web.dto.UserResponseDTO;
import com.cdcdevtools.jookolante.service.UserService;
import com.cdcdevtools.jookolante.exception.ResourceNotFoundException;
import com.cdcdevtools.jookolante.exception.DuplicateResourceException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            UserResponseDTO createdUser = userService.createUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (DuplicateResourceException e) {
            // Cette exception sera attrapée par le GlobalExceptionHandler
            throw e;
        }
    }
    
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        try {
            UserResponseDTO user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (ResourceNotFoundException e) {
            throw e;
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserDTO userDTO) {
        try {
            UserResponseDTO updatedUser = userService.updateUser(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (ResourceNotFoundException | DuplicateResourceException e) {
            throw e;
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            throw e;
        }
    }
    
    // Endpoints supplémentaires utiles
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        try {
            UserResponseDTO user = userService.getUserByEmail(email);
            return ResponseEntity.ok(user);
        } catch (ResourceNotFoundException e) {
            throw e;
        }
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username) {
        try {
            UserResponseDTO user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user);
        } catch (ResourceNotFoundException e) {
            throw e;
        }
    }
}