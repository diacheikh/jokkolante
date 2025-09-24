package com.cdcdevtools.jookolante.security.service;

import com.cdcdevtools.jookolante.domain.entity.User;
import com.cdcdevtools.jookolante.repository.UserRepository;
import com.cdcdevtools.jookolante.security.model.UserPrincipal;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new UserPrincipal(user);
    }
}