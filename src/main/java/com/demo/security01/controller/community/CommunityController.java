package com.demo.security01.controller.community;

import com.demo.security01.config.annotation.LoungeWriteRequestAnno;
import com.demo.security01.entity.comment.CommentEntity;
import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.category.CategoryDto;
import com.demo.security01.model.dto.comment.response.CommentResponseDto;
import com.demo.security01.model.dto.community.LoungeWriteRequest;
import com.demo.security01.repository.category.CategoryRepositoryCustom;
import com.demo.security01.service.category.CategoryService;
import com.demo.security01.service.community.CommentService;
import com.demo.security01.service.community.LikeService;
import com.demo.security01.service.community.LoungeService;
import com.demo.security01.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private final CategoryService categoryService;
    private final LoungeService loungeService;
    private final UserService userService;
    private final LikeService likeService;
    private final CommentService commentService;

    @Resource(name = "loungeWriteValidator")
    private Validator loungeWriteValidator;

    @InitBinder("loungeWriteRequest")
    public void initBinder(WebDataBinder binder){
        binder.addValidators(loungeWriteValidator);
    }

    // 불필요한 과정이라 삭제
    /*@ModelAttribute
    public void communityHeader(Model model, CategoryDto categoryDto){
        log.info("============= communityHeader ===================");
        categoryDto.setCategoryIdx(2L);
        log.info("subCate = {}", categoryService.getSubCategoryList(categoryDto.getCategoryIdx()));
        model.addAttribute("subCate", categoryService.getSubCategoryList(categoryDto.getCategoryIdx()));
    }*/

    // 커뮤니티 메인, 개발자 라운지
    @GetMapping({"/lounge", ""})
    public String communityLounge(Model model){
//        List<LoungeEntity> allLounge = loungeService.findAllLounge();
//        model.addAttribute("allLounge", allLounge);

        return "community/commuLounge";
    }



    // 라운지 작성 폼
    // custom ArgumentResolver 를 사용하는 경우 @ModelAttribute, @RequestBody 사용불가, 해당 어노테이션에 해당하는 argumentResolver 가 이미 있기 때문
    @GetMapping("/lounge/write")
    public String getLoungeWriteForm(HttpServletRequest request, @ModelAttribute("loungeWriteRequest") LoungeWriteRequest loungeWriteRequest, Model model) throws IOException {

        log.info("\tgetLoungeWriteForm called........");
        if (request.getUserPrincipal() == null){
            model.addAttribute("msg", "로그인이 필요합니다...");
            model.addAttribute("url", request.getContextPath() + "/community/lounge");
            return "error/loginError";
        }
        model.addAttribute("loungeWriteRequest", loungeWriteRequest);
        return "community/loungeWriteForm";
    }

    // 라운지 작성글 작성
    @PostMapping("lounge/writeProc")
    public String loungeWrite(@Valid @ModelAttribute("loungeWriteRequest") LoungeWriteRequest loungeWriteRequest,
                              BindingResult result, HttpServletRequest request, Model model, Principal principal){
        String username = principal.getName();
        log.info("\t loungeWrite called.....");

        log.info(loungeWriteRequest.getContents());

        if (result.hasErrors()){
            if (result.hasFieldErrors("cateCode")){ 
                loungeWriteRequest.setCateCode(5L);
                log.info("loungeWriteRequest.getCateCode() = {}", loungeWriteRequest.getCateCode());
            }
            result.getFieldErrors().forEach(fieldError -> {
                log.info("errorField = {}", fieldError.getField());
                String[] errorCodeArr = fieldError.getCodes();
                for (int i=0; i<errorCodeArr.length; i++) {
                    log.info("errorCode[{}] = {}", i, errorCodeArr[i]);
                }
                log.info("-----------------------------------------");
            });

            return "community/loungeWriteForm";
        }

//        if (result.hasFieldErrors("cateCode")){
//            loungeWriteRequest.setCateCode(5L);
//        }


        loungeService.loungeSave(loungeWriteRequest, username);
        request.setAttribute("msg", "라운지 게시글 작성 완료");
        request.setAttribute("url", request.getContextPath() + "/community/lounge");
        return "community/loungeWriteOk";
    }

    // 라운지 작성글 조회
    @GetMapping("/lounge/{loungeId}")
    public String getLounge(@PathVariable Long loungeId, Model model, Principal principal, HttpServletRequest
                             request){

        if (request.getUserPrincipal() == null){
            model.addAttribute("msg", "로그인이 필요합니다...");
            model.addAttribute("url", request.getContextPath() + "/community/lounge");
            return "error/loginError";

        }

        String username = principal.getName();

        LoungeEntity findLounge = loungeService.getLounge(loungeId);
        boolean isCheck = loungeService.isCheckLike(loungeId, username);
        List<CommentResponseDto> comments = commentService.getCommentList(loungeId);


        model.addAttribute("findLounge", findLounge);
        model.addAttribute("isLikeCheck", isCheck);
//        model.addAttribute("comments", comments);
        return "community/loungeReadForm";
    }

    // QnA
    @GetMapping("/QnA")
    public String communityQnA(){
        return "community/commuQnA";
    }
}
