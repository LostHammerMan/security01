package com.demo.security01.validator.user;

import com.demo.security01.model.dto.user.modifyUser.ModifyUserEmailDto;
import com.demo.security01.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Component(value = "modUserEmailValidator")
public class ModUserEmailValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ModifyUserEmailDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.info("===== modUserEmailValidator called.. ========");
        ModifyUserEmailDto modifyUserEmailDto = (ModifyUserEmailDto) target;
        log.info("ModifyUserValidator = {}", modifyUserEmailDto);
        checkEmail(modifyUserEmailDto, errors);

    }

    public void checkEmail(ModifyUserEmailDto modifyUserEmailDto, Errors errors){

        // 세션 가저오기
        // 컨트롤러 및 서비스에서 getSession 을 하지 않고, 세션정보를 가져오는 메서드
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession(true);

        String modifiedEmailAddr = modifyUserEmailDto.getModifiedEmailAddr();
        log.info("modifiedEmailAddr = {}", modifiedEmailAddr);

        // null 체크
        if (!StringUtils.hasText(modifiedEmailAddr)) {
            errors.rejectValue("modifiedEmailAddr", "NotBlank");
            return;
        }

        String authCode = modifyUserEmailDto.getAuthCode();
        log.info("authCode = {}", authCode);
        if (!StringUtils.hasText(authCode)) {
            errors.rejectValue("authCode", "NotBlank");
            return;
        }
//        Map<String, String> modifyEmailCheckOk = (Map<String, String>) session.getAttribute("emailCheck");
//
//        String sessionAuthCode = modifyEmailCheckOk.get(modifiedEmailAddr);
//
//
//        if (!modifyEmailCheckOk.containsKey(modifiedEmailAddr)){
//            errors.rejectValue("modifiedEmailAddr", "NeedAuth");
//        }
//
//        if (!authCode.equals(sessionAuthCode)){
//            errors.rejectValue("modifiedEmailAddr", "NotAuth");
//        }

    }
}
