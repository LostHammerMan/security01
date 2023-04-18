package com.demo.security01.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
//    ROLE_ADMIN, ROLE_MANAGER

    USER("ROLE_USER"),
//    ADMIN("ROLE_ADMIN", "관리자"),
    ADMIN("ROLE_ADMIN"),
    MANAGER("ROLE_MANAGER");
//    MANAGER("ROLE_MANAGER", "매니저");

    private final String role;
}
