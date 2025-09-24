package com.cdcdevtools.jookolante.service;

import com.cdcdevtools.jookolante.web.dto.UserDTO;
import com.cdcdevtools.jookolante.domain.entity.User;
import com.cdcdevtools.jookolante.domain.enums.UserRole;
import com.cdcdevtools.jookolante.exception.DuplicateResourceException;
import com.cdcdevtools.jookolante.exception.ResourceNotFoundException;
import com.cdcdevtools.jookolante.repository.UserRepository;
import com.cdcdevtools.jookolante.web.dto.UserResponseDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static Long createdUserId;

    @Test
    @Order(1)
    public void testCreateUser_success() {
        UserDTO dto = new UserDTO();
        dto.setUsername("ctd");
        dto.setEmail("ctd@example.com");
        dto.setPassword("secure123");
        dto.setRole(UserRole.REGION_ADMIN);
        dto.setZoneId(1L);
        dto.setAssociationId(1L);

        UserResponseDTO response = userService.createUser(dto);
        createdUserId = response.getId();

        User user = userRepository.findById(createdUserId).orElse(null);
        assertNotNull(user);
        assertEquals("ctd", user.getUsername());
        assertTrue(user.getPassword().startsWith("$2a$")); // BCrypt
    }

    @Test
    @Order(2)
    public void testCreateUser_duplicateEmail() {
        UserDTO dto = new UserDTO();
        dto.setUsername("another");
        dto.setEmail("ctd@example.com"); // same email
        dto.setPassword("pass");
        dto.setRole(UserRole.REGION_ADMIN);
        dto.setZoneId(1L);
        dto.setAssociationId(1L);

        assertThrows(DuplicateResourceException.class, () -> userService.createUser(dto));
    }

    @Test
    @Order(3)
    public void testUpdateUser_success() {
        UserDTO dto = new UserDTO();
        dto.setUsername("ctd_updated");
        dto.setEmail("ctd_updated@example.com");
        dto.setPassword("newpass");
        dto.setRole(UserRole.COUNTRY_ADMIN);
        dto.setZoneId(1L);
        dto.setAssociationId(1L);

        UserResponseDTO updated = userService.updateUser(createdUserId, dto);
        assertEquals("ctd_updated", updated.getUsername());
        assertEquals("ctd_updated@example.com", updated.getEmail());

        User user = userRepository.findById(createdUserId).orElseThrow();
        assertTrue(passwordEncoder.matches("newpass", user.getPassword()));
    }

    @Test
    @Order(4)
    public void testGetUserByEmail_success() {
        UserResponseDTO user = userService.getUserByEmail("ctd_updated@example.com");
        assertEquals("ctd_updated", user.getUsername());
    }

    @Test
    @Order(5)
    public void testDeleteUser_success() {
        userService.deleteUser(createdUserId);
        assertFalse(userRepository.existsById(createdUserId));
    }

    @Test
    @Order(6)
    public void testGetUserById_notFound() {
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(9999L));
    }
}