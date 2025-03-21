package com.demo.security01.controller.user;

import com.demo.security01.entity.tag.User_Skilltag;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.user.JoinUserDto;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserDto;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserProfileDto;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserPwdDto;
import com.demo.security01.repository.user.UserProfileRepository;
import com.demo.security01.repository.user.UserRepositoryCustomImpl;
import com.demo.security01.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final UserRepositoryCustomImpl userRepositoryCustom;
    private final UserProfileRepository profileRepository;
    private final MessageSourceAccessor messageSourceAccessor;



    @Resource(name = "joinValidator")
    private Validator joinValidator;

   /* @Resource(name = "modUserEmailValidator")
    private Validator modUserEmailValidator;*/

    @Resource(name = "modUserPwdValidator")
    private Validator modUserPwdValidator;

    @Resource(name = "modUserProfileValidator")
    private Validator modUserProfileValidator;


//    @InitBinder(value = {"",""}) // validator 가 2개 이상의 dto 에 적용되는 경우
    @InitBinder("joinUserDto") // 하나에만 적용하는 경우
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(joinValidator);
    }

    @InitBinder("modifyUserPwdDto")
    public void initBinder2(WebDataBinder binder){
        binder.addValidators(modUserPwdValidator);
    }

    @InitBinder("modifyUserProfileDto")
    public void initBinder3(WebDataBinder binder){
        log.info("======= modUserProfileValidator =========");
        binder.addValidators(modUserProfileValidator);
    }

    /*@InitBinder("modifyUserDto")
    public void initBinder2(WebDataBinder binder){
        binder.addValidators(modUserEmailValidator);
    }*/

    // 스프링 시큐리티가 해당 주소를 낚아챔 - Security Config 파일 생성 후 작동 안함
//    @GetMapping("/loginForm")
    @RequestMapping("/loginForm")
    public String loginForm(@RequestParam(value = "exception", required = false) String exception) {
        return "user/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(@ModelAttribute("joinUserDto") JoinUserDto joinUserDto) {
        log.info("======== joinForm ===========");
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
        request.setAttribute("msg", "회원 가입이 완료되었습니다");
        request.setAttribute("url", request.getContextPath() + "/user/loginForm");
        return "user/joinOk";
//        return "redirect:user/loginForm";
//        return "redirect:" + request.getContextPath() +"/user/loginForm";
    }

    // 회원수정 form
//    public String modifyForm(Principal principal, @ModelAttribute("modifyUserDto") ModifyUserDto modifyUserDto, Model model){
    @GetMapping("/modifyForm")
	public String modifyForm(Principal principal, Model model){
    	log.info("========= modifyForm ==============");
        String username = principal.getName();
        User loginUser =  userService.userDetailsByUsername(username);
        List<Long> tagId = new ArrayList<>(); 
        for(User_Skilltag tags : loginUser.getUser_skillTag()) {
        	log.info("tagId = " + tags.getSkillTag().getIdx()); 
        	tagId.add(tags.getSkillTag().getIdx());
        }
        model.addAttribute("tagId" ,tagId);
        model.addAttribute("loginUser", loginUser);
        
        return "user/modifyForm";
    }

    // 회원수정 - 비밀번호 변경 폼
    @GetMapping("/modifyPwdForm")
    public String modifyPwdForm(Principal principal, @ModelAttribute("modifyUserPwdDto") ModifyUserPwdDto modifyUserPwdDto){
        return "user/modifyPwdForm";
    }

    // 회원수정 - 비밀번호 변경
    @PostMapping("/modifyPwdProc")
    public String modifyPwdProc(@Valid @ModelAttribute("modifyUserPwdDto") ModifyUserPwdDto modifyUserPwdDto, BindingResult result, Principal principal, HttpServletRequest request){
        log.info("NowPw = {}", modifyUserPwdDto.getNowPw());
        log.info("NewPw = {}", modifyUserPwdDto.getNewPw());
        log.info("ConPw = {}", modifyUserPwdDto.getConfPw());
        String errorCode = null;
        if (result.hasErrors()){
            for (FieldError fieldError : result.getFieldErrors()) {
                log.info("error field = {}", fieldError.getField());
                log.info("error code = {}", fieldError.getCodes()[0]);
                errorCode = fieldError.getCodes()[0];
            }
            return "user/modifyPwdForm";

        }
        userService.updatePwd(modifyUserPwdDto, principal.getName());
        return "redirect:" + request.getContextPath() + "/user/modifyForm";


    }

    // 회원수정 - 프로필 수정
    @GetMapping("/modifyProfile")
    public String modifyProfile(@ModelAttribute("modifyUserProfileDto") ModifyUserProfileDto modifyUserProfileDto, Principal principal, Model model){
        String username = principal.getName();
        User loginUser = userService.userDetailsByUsername(username);

        model.addAttribute("loginUser", loginUser);
//        model.addAttribute("profileErrorMsg", "에러 있다아아아");

        return "user/modifyProfile";
    }

    @PostMapping("/modifyProfileProc")
    public String modifyProfileProc(@Valid @ModelAttribute("modifyUserProfileDto") ModifyUserProfileDto modifyUserProfileDto, BindingResult result, Principal principal, HttpServletRequest request, Model model) throws IOException {
        log.info("======= modifyProfileProc ===========");
        log.info("\t > {}", modifyUserProfileDto);
        //String originalFileName = modifyUserProfileDto.getProfileImg().getOriginalFilename(); // 서버 재시작시 NullPointerException
        String storeFileName = modifyUserProfileDto.getProfileImgName();
//        modifyUserProfileDto.setProfileImgName(originalFileName);

        String username = principal.getName();
//        User loginUser = userService.userDetailsByUsername(username);
        log.info("\t modifyUserProfileDto = {}", modifyUserProfileDto);
//        log.info("\t\t originalFileName = {}", originalFileName);
        log.info("\t\t storeFileName = {}", storeFileName);

        if (result.hasErrors()){
            String errorCode = null;
            log.info("\t modifyProfile has errors.......");
            result.getFieldErrors().forEach(fieldError -> {
                log.info("errorField = {}", fieldError.getField());
                String[] errorCodeArr = fieldError.getCodes();
                for (int i=0; i<errorCodeArr.length; i++) {
                    log.info("errorCode[{}] = {}", i, errorCodeArr[i]);
                }

                //
                String msg = messageSourceAccessor.getMessage(errorCodeArr[0]);

                model.addAttribute("profileErrorMsg", msg);

                log.info("-----------------------------------------");
            });
            return "user/modifyProfile";
        }

        userService.profileModify(modifyUserProfileDto, username);
        log.info("\t modifyProfile Success");
//        request.
        return "redirect:" + request.getContextPath() + "/user/modifyProfile";

//        return "user/modifyProfile";
    }

    @Secured("ROLE_ADMIN") // ROLE_ADMIN 권한만 접근 가능 , 메서드에 사용, 하나의 권한만 사용 가능
    @GetMapping("/info")
    public @ResponseBody String info() {
        return "개인정보";
    }
    
    // username 찾기
    @GetMapping("/findId")
    public String findIdForm() {
    	return "user/findIdForm";
    }
    
    // 비밀번호 찾기 클릭
    @GetMapping("/findPw")
    public String findPwForm() {
    	return "user/findPwForm"; 
    }
    
    // 비밀번호 전송
    @GetMapping("/findPwProc")
    public String findPwProc(@RequestParam String email) throws Exception {
    	log.info("email = " + email);
    	userService.sendResetPwLink(email);
    	
    	return null;
    	
    }
    
    // 비밀번호 재설정 링크 클릭
    
    @GetMapping("/resetPw")
    public String resetPwForm(@RequestParam String tempToken, Model model) {
    	log.info("tempToken = " + tempToken);
    	
    	// redis 에 저장된 token 과 일치 여부
    	boolean isvalid = userService.validateToken(tempToken);
    	
    	if(!isvalid) {
    		model.addAttribute("msg", "유효하지 않은 링크가 확인되었습니다. 비밀번호 재설정을 다시 한번 클릭해주세요");
    		model.addAttribute("url", "${root}user/loginForm");
    		return "error/invalidTokenError";
    	}
    	
    	return "user/resetPwForm";
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
