package com.cdcdevtools.jookolante.security.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class TokenValidationResponse {
    private boolean valid;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;
}