package com.demo.security01.controller.user;

import com.demo.security01.entity.User;
import com.demo.security01.model.dto.user.JoinUserDto;
import com.demo.security01.model.dto.user.ModifyUserDto;
import com.demo.security01.repository.user.UserRepositoryCustomImpl;
import com.demo.security01.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final UserRepositoryCustomImpl userRepositoryCustom;

    @Resource(name = "joinValidator")
    private Validator joinValidator;

//    @InitBinder(value = {"",""}) // validator 가 2개 이상의 dto 에 적용되는 경우
    @InitBinder("joinUserDto") // 하나에만 적용하는 경우
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(joinValidator);
        /*log.info("======= initBinder Called......============");
        String objectName = binder.getObjectName();

        if(objectName.equals("joinUserDto")) {
            binder.addValidators(joinValidator);
        }*/
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

    // dto 사용한 회원가입
    @PostMapping("/joinProc")
    public String Join(@Valid @ModelAttribute("joinUserDto") JoinUserDto joinUserDto, BindingResult result, HttpServletRequest request) {
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
//        return "redirect:user/loginForm";
        return "redirect:" + request.getContextPath() +"/user/loginForm";
    }

    // 회원수정
    @GetMapping("/modifyForm")
    public String modifyForm(Principal principal, @ModelAttribute("modifyUserDto") ModifyUserDto modifyUserDto, Model model){
        String username = principal.getName();
        User loginUser =  userService.userDetailsByUsername(username);
        log.info("user = {}", loginUser);
        model.addAttribute("loginUser", loginUser);
//        User findUser = userService.userDetails(userId);
//        log.info("findUser = {}", findUser);
//        model.addAttribute("findUser", findUser);
        return "user/modifyForm";
    }

    @PostMapping("/modifyProc")
    public String modifyProc(@ModelAttribute("modifyUserDto") ModifyUserDto modifyUserDto){

        return "user/modifyForm";
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
