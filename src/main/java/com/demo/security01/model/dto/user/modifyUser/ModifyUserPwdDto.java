package com.demo.security01.model.dto.user.modifyUser;

import lombok.Data;
import lombok.Getter;

@Data
public class ModifyUserPwdDto {

    String nowPw;
    String newPw;
    String confPw;
    String captchaCode;
}
