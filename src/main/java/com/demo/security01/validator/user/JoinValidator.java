package com.demo.security01.validator.user;

import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.user.JoinUserDto;
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
import java.util.Map;
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
        log.info("joinUserDto = {}", joinUserDto);

        checkUsername(joinUserDto, errors);
        checkPwd(joinUserDto.getPassword(), errors);
        checkSkill(joinUserDto, errors);
        checkEmail(joinUserDto, errors);
    }

    // username validation(공백, 정규식, 존재여부)
    private void checkUsername(JoinUserDto joinUserDto, Errors errors){
        log.info("========= UserJoinValidator ============");

        String regExp = "^([a-zA-Z0-9]{4,12})$";
        String username = joinUserDto.getUsername();
        log.info("Username = {}", username);

        if (!StringUtils.hasText(joinUserDto.getUsername())){
            errors.rejectValue("username", "NotBlank");
            return;
        } else if (!Pattern.matches(regExp, username)) {
            errors.rejectValue("username", "Pattern");
            return;
        } else if(userService.existByUsername2(joinUserDto.getUsername())){
            errors.rejectValue("username", "UsernameExist");
            return;
        }
    }

    // password validation
    private void checkPwd(String password, Errors errors){

        String pwdRegExp = "^([a-zA-Z0-9@$!%?&]){4,}$";

        if (!StringUtils.hasText(password)){
            errors.rejectValue("password", "NotBlank");
        } else if (!Pattern.matches(pwdRegExp, password)) {
            errors.rejectValue("password", "Pattern");
        }
    }

    // email
    private void checkEmail(JoinUserDto joinUserDto, Errors errors) {
        log.info("========= checkEmail ==========");

        if (!StringUtils.hasText(joinUserDto.getEmail_id())){
            errors.rejectValue("email_id", "id_NotBlank");
            return;
        }
        
        
        if (!StringUtils.hasText(joinUserDto.getEmail_domain())){
            errors.rejectValue("email_domain","domain_NotBlank");
            return;
        }

        String email_addr = joinUserDto.getEmail_id() + joinUserDto.getEmail_domain();
        log.info("email_addr = {}", email_addr);

        String auth_code = joinUserDto.getAuth_code();
        log.info("auth_code = {}", auth_code);


        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();

        @SuppressWarnings("unchecked")
        Map<String, String> emailCheck = (Map<String, String>) session.getAttribute("emailCheck");
        log.info("emailCheck = {}", emailCheck);

        if (!emailCheck.containsKey(email_addr)) {
            errors.rejectValue("email_addr", "NeedAuth");
            return;
        }

        String session_auth_code = emailCheck.get(email_addr);
        log.info("session_auth_code = {}", session_auth_code);

        if (!auth_code.equals(session_auth_code)) {
            errors.rejectValue("email_addr", "NotAuth");
        }
    }

    // skill
    private void checkSkill(JoinUserDto joinUserDto, Errors error) {
    	if(joinUserDto.getSkillTagIdx().isEmpty()) {
    		error.rejectValue("skillTagIdx", "NotEmpty");
    		return;
    	}
    }


    // Controller 가 아닌 곳에서 request session 정보 가져오기
    public User getSessionUser(){

        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpSession session = servletRequestAttributes.getRequest().getSession(true);
        return (User) session.getAttribute("UserVo");

    }
}
