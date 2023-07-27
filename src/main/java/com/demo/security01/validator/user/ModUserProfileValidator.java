package com.demo.security01.validator.user;

import com.demo.security01.model.dto.user.modifyUser.ModifyUserProfileDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Slf4j
@Component(value = "modUserProfileValidator")
public class ModUserProfileValidator implements Validator {

    public static final String regExp = "^([가-힣a-zA-Z0-9]{2,20})$";

    @Override
    public boolean supports(Class<?> clazz) {
        return ModifyUserProfileDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ModifyUserProfileDto modifyUserProfileDto = (ModifyUserProfileDto) target;

        // NickName Check
        checkNickName(modifyUserProfileDto, errors);
    }

    // NickNameCheck
    private void checkNickName(ModifyUserProfileDto modifyUserProfileDto, Errors errors){
        // replaceAll("\\s", "") : whitespace 를 "" 로 변환해 공백제거
        // "\\s" : whitespace

        String nickName = modifyUserProfileDto.getNickName();
        String result = nickName.replaceAll("\\s", "");
        if (!StringUtils.hasText(nickName)){
            errors.rejectValue("NickName", "NotBlank");
            return;
        }else if (!Pattern.matches(regExp, result)){
            errors.rejectValue("NickName", "Pattern");
            return;
        }

    }


}
