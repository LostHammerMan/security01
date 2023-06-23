package com.demo.security01.validator;

import com.demo.security01.model.dto.user.modifyUser.ModifyUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Slf4j
@Component(value = "modUserAddrValidator")
public class ModUserAddrValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ModifyUserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.info("====== modUserAddrValidator called.. ==========");
        ModifyUserDto modifyUserDto = (ModifyUserDto) target;

    }
}

