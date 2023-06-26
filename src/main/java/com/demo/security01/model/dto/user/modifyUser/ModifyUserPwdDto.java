package com.demo.security01.model.dto.user.modifyUser;

import lombok.Getter;

@Getter
public class ModifyUserPwdDto {

    String nowPw;
    String newPw;
    String confPw;
    String captchaCode;
}
