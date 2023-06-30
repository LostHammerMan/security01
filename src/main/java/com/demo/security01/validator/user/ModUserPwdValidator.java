package com.demo.security01.validator.user;

import com.demo.security01.config.security.auth.PrincipalDetails;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserPwdDto;
import com.demo.security01.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import nl.captcha.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

@Slf4j
@Component(value = "modUserPwdValidator")
public class ModUserPwdValidator implements Validator {

    @Autowired
    private UserService userService;

    private final String regExp = "^([a-zA-Z0-9]{4,12})$";


    @Override
    public boolean supports(Class<?> clazz) {
        return ModifyUserPwdDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ModifyUserPwdDto modifyUserPwdDto = (ModifyUserPwdDto) target;
        checkNowPwd(modifyUserPwdDto, errors);
        checkNewPwd(modifyUserPwdDto, errors);
        checkConfPw(modifyUserPwdDto, errors);
        checkCaptchaCode(modifyUserPwdDto,errors);
    }

    // 현재 비밀번호 - null, 현재 비밀번호와 일치 여부
    private void checkNowPwd(ModifyUserPwdDto modifyUserPwdDto, Errors errors){

        String nowPw = modifyUserPwdDto.getNowPw();

        // null 값
        if (!StringUtils.hasText(nowPw)){
            errors.rejectValue("nowPw", "NotBlank");
            return;
        }

        // 현재 비밀번호와 일치여부
        if (!userService.isMyPassword(nowPw, getCurrentUsername())){
            errors.rejectValue("nowPw", "NotMatch");
            return;
        }
    }
    // 새로운 비밀번호 확인 - null, 정규식, 현재 비밀번호와 일치여부
    public void checkNewPwd(ModifyUserPwdDto modifyUserPwdDto, Errors errors){
        String newPw = modifyUserPwdDto.getNewPw();
        // null, 정규식
        if (!StringUtils.hasText(newPw)){
            errors.rejectValue("newPw", "NotBlank");
            return;
        } else if (!Pattern.matches(regExp, newPw)) {
            errors.rejectValue("newPw", "Pattern");
            return;
        }
        // 현재비밀번호 - 일치시 에러
        if (userService.isMyPassword(newPw, getCurrentUsername())){
            errors.rejectValue("newPw", "Equal");
            return;
        }
    }

    // 새로운 비밀번호 확인 - null, newPw 와 확인
    public void checkConfPw(ModifyUserPwdDto modifyUserPwdDto, Errors errors){
        String newPw = modifyUserPwdDto.getNewPw();
        String confPw = modifyUserPwdDto.getConfPw();

        if (!StringUtils.hasText(confPw)){
            errors.rejectValue("confPw", "NotBlank");
            return;
        }

        if (!newPw.equals(confPw)){
            errors.rejectValue("confPw", "NotMatch");
            return;
        }

    }


    // 캡차 코드 확인
    private void checkCaptchaCode(ModifyUserPwdDto modifyUserPwdDto, Errors errors){
        log.info("========== checkCaptchaCode called... =================");
        String captchaCode = modifyUserPwdDto.getCaptchaCode();
        log.info("captchaCode = {}", captchaCode);



        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession(true);

        Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
        String captchaAnswer = captcha.getAnswer();

        log.info("captcha = {}", captcha);

        if (!captchaCode.equals(captchaAnswer)){
            errors.rejectValue("captchaCode", "NotMatch");
            return;
        }
    }

    // 현재 접속중인 username
    private String getCurrentUsername(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) return null;

        Object principal = auth.getPrincipal();
        String currentUsername = null;

        PrincipalDetails principalDetails = (PrincipalDetails) principal;
        currentUsername = principalDetails.getUsername();
        log.info("currentUsername = {}", currentUsername);
        return currentUsername;



    }
}
