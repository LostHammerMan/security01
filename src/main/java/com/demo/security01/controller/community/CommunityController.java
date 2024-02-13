package com.demo.security01.controller.community;

import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.category.CategoryDto;
import com.demo.security01.model.dto.community.LoungeWriteRequest;
import com.demo.security01.repository.category.CategoryRepositoryCustom;
import com.demo.security01.service.category.CategoryService;
import com.demo.security01.service.community.LoungeService;
import com.demo.security01.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        List<LoungeEntity> allLounge = loungeService.findAllLounge();
        model.addAttribute("allLounge", allLounge);

        return "community/commuLounge";
    }

    // 라운지 작성 폼
    @GetMapping("/lounge/write")
    public String getLoungeWriteForm(HttpServletRequest request,@ModelAttribute("loungeWriteRequest") LoungeWriteRequest loungeWriteRequest, Model model) throws IOException {

        log.info("\tgetLoungeWriteForm called........");
        if (request.getUserPrincipal() == null){
            model.addAttribute("msg", "로그인이 필요합니다...");
            model.addAttribute("url", request.getContextPath() + "/community/lounge");
            return "error/loginError";
        }

        return "community/loungeWriteForm";
    }

    // 라운지 작성글 작성
    @PostMapping("lounge/writeProc")
    public String loungeWrite(Principal principal, @ModelAttribute("loungeWriteRequest") LoungeWriteRequest loungeWriteRequest, HttpServletRequest request){
        String username = principal.getName();

        loungeService.loungeSave(loungeWriteRequest, username);
        request.setAttribute("msg", "라운지 게시글 작성 완료");
        request.setAttribute("url", request.getContextPath() + "/community/lounge");
        return "community/loungeWriteOk";
    }

    // QnA
    @GetMapping("/QnA")
    public String communityQnA(){
        return "community/commuQnA";
    }
}
