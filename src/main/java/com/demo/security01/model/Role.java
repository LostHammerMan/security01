package com.demo.security01.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Role implements GrantedAuthority {
//    ROLE_ADMIN, ROLE_MANAGER

    USER("ROLE_USER"),
//    ADMIN("ROLE_ADMIN", "관리자"),
    ADMIN("ROLE_ADMIN"),
    MANAGER("ROLE_MANAGER");
//    MANAGER("ROLE_MANAGER", "매니저");

    private final String role;

    private Role(String role){
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
