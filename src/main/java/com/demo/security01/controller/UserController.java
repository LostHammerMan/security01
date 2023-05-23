package com.demo.security01.controller;

import com.demo.security01.model.dto.JoinUserDto;
import com.demo.security01.repository.UserRepositoryCustomImpl;
import com.demo.security01.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Slf4j
//@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final UserRepositoryCustomImpl userRepositoryCustom;

    @Resource(name = "joinValidator")
    private Validator joinValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        log.info("======= initBinder Called......============");
        String objectName = binder.getObjectName();

        if(objectName.equals("joinUserDto")) {
            binder.addValidators(joinValidator);
        }
//        } else if(objectName.equals("emailDto")) {
//            binder.addValidators(emailValidator);
//        }
    }

    // 스프링 시큐리티가 해당 주소를 낚아챔 - Security Config 파일 생성 후 작동 안함
//    @GetMapping("/loginForm")
    @RequestMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(@ModelAttribute("joinUserDto") JoinUserDto joinUserDto) {
        return "user/joinForm";
    }

    // 회원가입
    /*@PostMapping("/join")
    public String Join(User user) {

        userService.save(user);
        log.info("user = {}", user.getRole());
        return "redirect:/loginForm";
    }*/

    // dto 사용한 회원가입
    @PostMapping("/joinProc")
    public String Join(@Valid @ModelAttribute("joinUserDto") JoinUserDto joinUserDto, BindingResult result) {
        log.info("=============joinProc called ============");
        log.info("join ={}", joinUserDto);

        if (result.hasErrors()){
            result.getFieldErrors().forEach(fieldError -> {
                log.info("errorField = {}", fieldError.getField());
                String[] errorCodeArr = fieldError.getCodes();
                for (int i=0; i<errorCodeArr.length; i++) {
                    log.info("errorCode[{}] = {}", i, errorCodeArr[i]);
                }
                log.info("-----------------------------------------");
            });

            return "user/joinForm";
        }

        userService.save(joinUserDto);
        return "redirect:/loginForm";
    }

    @Secured("ROLE_ADMIN") // ROLE_ADMIN 권한만 접근 가능 , 메서드에 사용, 하나의 권한만 사용 가능
    @GetMapping("/info")
    public @ResponseBody String info() {
        return "개인정보";
    }

    /*@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // data() 가 실행되기 전에 실행, 권한 여러 개 설정 가능
//    @PostAuthorize() // data() 실행 이후 실행
    @GetMapping("data")
    public @ResponseBody String data(){
        return "데이터 정보";
    }

    @GetMapping("/test")
    public String test() {
        log.info("## test called..");
        return "user/test";
    }*/
}
