package com.demo.security01.model.dto.user.modifyUser;

import lombok.Data;

@Data
public class ModifyUserEmailDto {
    private int userIdx;
    private String modifiedEmailAddr;
    private String authCode;
}
