package com.demo.security01.model.dto.user.modifyUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModifyUserDto {

    private int userIdx;
    private String username;
    private String password;
    private String emailAddr;
    private String modifiedEmailAddr;
    private String authCode;
    private MultipartFile profileFile;
    private String zipCode;
    private String postAddr1;
    private String postAddr2;
    private List<Long> skillTagId;

}
