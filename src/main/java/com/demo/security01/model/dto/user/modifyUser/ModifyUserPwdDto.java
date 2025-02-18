package com.demo.security01.model.dto.user.modifyUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ModifyUserPwdDto {

    String nowPw;
    String newPw;
    String confPw;
    String captchaCode;
    String tempToken;
    
    
	public ModifyUserPwdDto(String newPw, String confPw, String tempToken) {
		this.tempToken = tempToken;
		this.newPw = newPw;
		this.confPw = confPw;
	}
    
    
}
