package com.example.instazoo.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleUser implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
