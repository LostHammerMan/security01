package com.demo.security01.controller.community;

import com.demo.security01.model.dto.category.CategoryDto;
import com.demo.security01.repository.category.CategoryRepositoryCustom;
import com.demo.security01.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private final CategoryService categoryService;

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
    public String communityLounge(){
        return "community/commuLounge";
    }

    @GetMapping("/lounge/write")
    public String loungeWrite(){
        return "community/loungeWriteForm";
    }

    // QnA
    @GetMapping("/QnA")
    public String communityQnA(){
        return "community/commuQnA";
    }
}
