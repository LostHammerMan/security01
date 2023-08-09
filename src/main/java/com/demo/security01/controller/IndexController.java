package com.demo.security01.controller;

import com.demo.security01.model.dto.category.CategoryDto;
import com.demo.security01.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {

    private final CategoryService categoryService;
    @GetMapping({"","/"})
    public String index(Model model) {
        log.info("========== index Controller ===============");
        log.info("categoryResult = {}", categoryService.getCategoryList());

//        List<CategoryDto> categoryDtos = categoryService.getCategoryList();
//        model.addAttribute("categoryDtos", categoryService.getCategoryList());
        /*for (CategoryDto cateResults : categoryService.getCategoryList()){
            model.addAttribute("cateResults", cateResults);
        }
*/
        /*for (CategoryDto results : categoryService.getCategoryList()){
            String categoryName = results.getCategoryName();
            model.addAttribute("categoryName", categoryName);
        }*/
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user(){
        return "user";
    }

   /* @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }*/

    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager";
    }

    @GetMapping("/flexTest")
    public String flexTest(){
        return "flexTest";
    }


}
