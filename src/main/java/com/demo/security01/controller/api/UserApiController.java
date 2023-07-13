package com.demo.security01.controller.api;

import com.demo.security01.config.captcha.CaptchaUtil;
import com.demo.security01.model.dto.reponseDto.ResponseEntityDto;
import com.demo.security01.model.dto.user.EmailDto;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserDto;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserEmailDto;
import com.demo.security01.model.test.MultipartFileTest.UploadFile;
import com.demo.security01.service.user.MailServiceImpl;
import com.demo.security01.service.user.UserProfileUploadService;
import com.demo.security01.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import nl.captcha.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserApiController {


    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileUploadService profileService;

    @Resource(name = "modUserEmailValidator")
    private Validator modUserEmailValidator;

    @InitBinder("modifyUserEmailDto")
    public void init(WebDataBinder binder){
        binder.addValidators(modUserEmailValidator);
    }

    // 이메일 인증번호 전송
    @PostMapping("/modifyEmailAuth")
    public String emailAuth(@RequestBody ModifyUserDto modifyUserDto, HttpSession session) throws Exception {
        log.info("=========== Modify emailAuth called..... ==============");

        String modifiedEmailAddr = modifyUserDto.getModifiedEmailAddr();
//        log.info("email_addr = " + email_addr);
        log.info("modifiedEmailAddr ={}", modifiedEmailAddr);

        String code = mailService.sendSimpleMessage(modifiedEmailAddr);
        log.info("code = {}", code);

        Map<String, String> emailCheck = new HashMap<>();
        emailCheck.put(modifiedEmailAddr, code);
        log.info("emailCheck = {}", emailCheck);

        session.setAttribute("emailCheck", emailCheck);
        session.setAttribute("authCode", code);

        return code;
    }

    // 인증번호 확인
    @GetMapping(value = {"/authNumCheck","/authNumCheck{inputAuthNum}"}) // {auth_code} 가 null 인 경우를 위해 "/authNumCheck" 사용
    public ResponseEntity<Object> authNumCheck(@PathVariable String inputAuthNum, HttpSession session){
        log.info("================= inputAuthNum called ===================");
        log.info("inputAuthNum = {}", inputAuthNum);

        String authCode = (String) session.getAttribute("authCode");
        log.info("authCode = {}", authCode);

        if (!authCode.equals(inputAuthNum)){
            throw new IllegalArgumentException("인증코드가 일치하지 않음");
//            throw new IllegalArgumentException(getMe);
            /*ResponseEntityDto responseEntityDto = ResponseEntityDto.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("인증코드가 일치하지 않음!!").build();

            throw new
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseEntityDto);*/
        }

        ResponseEntityDto responseEntityDto = ResponseEntityDto.builder()
                .status(HttpStatus.OK.value())
                .message("인증 성공!!").build();

        return ResponseEntity.status(HttpStatus.OK).body(responseEntityDto);



//        Response response = new Response();

       /* if (auth_code == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("입력값이 없음");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 400
//            throw new IllegalArgumentException("입력 값이 없음");
            throw new NullPointerException("인증코드가 입력되지 않았습니다.");
//            throw new MissingPathVariableException(au, auth_code);

        } else if (!authNum.equals(auth_code)) {
            return null;
//            response.setStatus(HttpStatus.NOT_FOUND.value());
//            response.setMessage("인증코드 다시 확인!");
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);  // 404
        } else if(authNum.equals("")){
            return new MissingPathVariableException(authNum, auth_code);
        }

//        response.setStatus(HttpStatus.OK.value());
//        response.setMessage("인증코드 확인되었습니다");

//        return new ResponseEntity<>(response, HttpStatus.OK); // 200*/

    }

    // 회원수정 - 주소
    @PostMapping("/modifyAddr")
    public String modifyAddr(@Valid @RequestBody ModifyUserDto modifyUserDto, Principal principal){
        log.info("========= modifyAddr called ==========");
        log.info("zipcode = {}", modifyUserDto.getZipCode());
        log.info("postAddr1 = {}", modifyUserDto.getPostAddr1());
        log.info("postAddr2 = {}", modifyUserDto.getPostAddr2());
        userService.addrModify(modifyUserDto, principal.getName());

        return "전송완료";
    }

    // 이메일 주소 수정시 인증
    @PostMapping("/emailAuth")
    public String emailAuth(@RequestBody EmailDto emailDto, HttpSession session) throws Exception {
        log.info("=========== emailAuth called..... ==============");
        log.info("sendAuthEmail, {}", emailDto);

        String email_id = emailDto.getEmail_id();
        String email_domain = emailDto.getEmail_domain();
        String email_addr = email_id + email_domain;
        log.info(email_addr);
//        log.info("email_addr = " + email_addr);

        String code = mailService.sendSimpleMessage(email_addr);
        log.info("code = {}", code);

        Map<String, String> emailCheck = new HashMap<>();
        emailCheck.put(email_addr, code);
        log.info("emailCheck = {}", emailCheck);

        session.setAttribute("emailCheck", emailCheck);
        session.setAttribute("authCode", code);

        return code;
    }

    // 회원수정 - 이메일
    @PostMapping("/modifyEmail")
    public ResponseEntity<Object> modifyEmail(@Valid @RequestBody ModifyUserEmailDto modifyUserEmailDto, /*BindingResult result,*/ HttpServletRequest request, Principal principal){
        log.info("================ modifyEmail called... ================");
        log.info("modifyEmail = {}", modifyUserEmailDto.getModifiedEmailAddr());
        log.info("authCode = {}", modifyUserEmailDto.getAuthCode());


        String username = principal.getName();
        userService.emailModify(modifyUserEmailDto, username);

        ResponseEntityDto responseEntityDto = ResponseEntityDto.builder()
                .status(HttpStatus.OK.value())
                .message("변경 성공!!").build();

        return ResponseEntity.status(HttpStatus.OK).body(responseEntityDto);
//        return "redirect:" + request.getContextPath() +"user/modifyForm";
    }

    // captcha 이미지 가져오는 메서드
    @GetMapping("/getCaptchaImg")
    @ResponseBody
    public void captchaImg(HttpServletRequest request, HttpServletResponse response) throws Exception{
        new CaptchaUtil().captchaImg(request, response);
    }

    // 전달받은 문자열로 음성 가져오는 메서드
    @GetMapping("/getCaptchaAudio")
    @ResponseBody
    public void captchaAudio(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
        String getAnswer = captcha.getAnswer();
        new CaptchaUtil().getAudioCaptCha(request, response, getAnswer);
    }

    // 프로필 이미지 업로드
    @ResponseBody
   @PostMapping("/updateProfileImg")
    public ResponseEntity<Object> updateProfileImg(@RequestParam("profileImg") MultipartFile profileImg) throws IOException {
        log.info("============= updateProfileImg ===============");
        log.info("\t profileImg = {}", profileImg);
        UploadFile uploadFile = profileService.uploadProfileImg(profileImg);
        log.info("\t uploadFile = {}", uploadFile);

        return ResponseEntity.ok(uploadFile);
    }

    // 이미지 파일 불러오기
    @ResponseBody
    @GetMapping("/profileImages/{fileName}")
//    public ResponseEntity<Object> showImage(@PathVariable String fileName) throws MalformedURLException {
    public ResponseEntity<Object> showImage(@PathVariable String fileName) throws MalformedURLException {
        org.springframework.core.io.Resource resource = null;
//   file:  http: 이런식의 Prefix로 프로토콜을 명시해주고 해당 리소스의 위치를 알려주는 URL방식을 통해서 리소스의 위치를 알려주는 방식
        resource = new UrlResource("file:" + profileService.getFullPath(fileName));
        log.info("======== showImage ============");
        log.info("\t fileName = {}", fileName);
        log.info("\t fullPath = {}", "file:///" + profileService.getFullPath(fileName));
//        String resultFile = URLEncoder.encode(resource, StandardCharsets.UTF_8);

//        return ResponseEntity.ok(resource);
        //return new UrlResource("file:" + profileService.getFullPath(fileName));
//        return ResponseEntity.ok("file:" + profileService.getFullPath(fileName));
//        return ResponseEntity.ok("file:///" + profileService.getFullPath(fileName));
        return ResponseEntity.ok(resource);
    }
//    {"/authNumCheck","/authNumCheck{inputAuthNum}"}
    // 이미지 파일 삭제
    @GetMapping(value = {"/deleteProfileImg","/deleteProfileImg/{fileName}" })
    public ResponseEntity<Object> deleteImg(@PathVariable String fileName) throws IOException {
        log.info("========= deleteImg ===========");
        log.info("deleteFileName = {}", fileName);
        profileService.deleteProfileImg(fileName);
        /*org.springframework.core.io.Resource resource = new UrlResource("file:" + profileService.getFullPath(fileName));
        log.info("resource = {}", resource);
        File file = new File(resource.getFile().toURI());
        file.delete();*/

        return ResponseEntity.ok("success");
    }






}
