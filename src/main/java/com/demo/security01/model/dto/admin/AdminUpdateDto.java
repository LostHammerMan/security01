package com.demo.security01.model.dto.admin;

import com.demo.security01.model.Role;
import lombok.Data;

@Data
public class AdminUpdateDto {
    private int id;
    private Role role;
}
