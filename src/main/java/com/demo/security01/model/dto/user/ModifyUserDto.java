package com.demo.security01.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModifyUserDto {

    private String username;
    private String password;
    private String email_addr;
    private String modified_email_addr;
    private String auth_code;
    private MultipartFile profileFile;
    private String zipCode;
    private String postAddr1;
    private String postAddr2;

}
