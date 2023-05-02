package com.demo.security01.validator;

import com.demo.security01.model.dto.JoinUserDto;
import com.demo.security01.repository.UserRepositoryCustom;
import com.demo.security01.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@Component(value = "joinValidator")
public class JoinValidator implements Validator {

    private final UserService userService;

    // 검증할 객체가 Validator 가 검증할 수 있는 클래스인지 검증
    @Override
    public boolean supports(Class<?> clazz) {
        return JoinUserDto.class.isAssignableFrom(clazz);
    }

    // 실제 검증 로직이 이루어지는 메서드, ValidationUtils 를 사용해 편리하게 작성 가능
    @Override
    public void validate(Object target, Errors errors) {
        log.info("=========== JoinValidator called ===========");

        JoinUserDto joinUserDto = (JoinUserDto) target;
        checkUsername(joinUserDto.getUsername(), errors);

    }

    // username validation(공백, 정규식, 존재여부)
    private void checkUsername(String username, Errors errors){

        String regExp = "^([a-zA-Z0-9]{4,12})$";

        if (!StringUtils.hasText(username)){
            errors.rejectValue("username", "NotBlank");
        } else if (!Pattern.matches(regExp, username)) {
            errors.rejectValue("username", "Pattern");
        } else if(userService.existByUsername(username)){
            errors.rejectValue("username", "UsernameExist");
        }
    }
}
