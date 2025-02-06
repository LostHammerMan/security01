package com.demo.security01.controller.test;

import com.demo.security01.entity.test.User_redis;
import com.demo.security01.model.dto.TestDto;
import com.demo.security01.model.dto.test.TestValidatorDto;
import com.demo.security01.model.test.MultipartFileTest.FileStore;
import com.demo.security01.model.test.MultipartFileTest.Item;
import com.demo.security01.model.test.MultipartFileTest.ItemForm;
import com.demo.security01.model.test.MultipartFileTest.UploadFile;
import com.demo.security01.repository.test.MultipartFileTest.ItemRepository;
import com.demo.security01.validator.test.TestEmailAddrValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/test")
public class TestController {

   /* @Resource(name = "testEmailAddrValidator")
    private TestEmailAddrValidator testEmailAddrValidator;
*/
    @Autowired
    private FileStore fileStore;

    @Autowired
    private ItemRepository itemRepository;
    
    // 레디스 테스트용
    @ResponseBody
    @PostMapping
    public User_redis addUserByRedis(@RequestBody User_redis user_redis) {
    	return null;
    }
    
    // 캐러셀 테스트용
    @GetMapping("/carouselTest")
    public String carouselTest() {
    	return "test/carouselTest";
    }

    @GetMapping("/divTest")
    public String divTest(){
        return "test/divTest";
    }

    @GetMapping("/divTest2")
    public String divTest2(){
        return "test/divTest2";
    }

    @GetMapping("/test")
    public String test(){
        return "test/button_test";
    }

    @GetMapping("/testDto")
    public String test2(){
        return "test/testDto";
    }

    @PostMapping("/testDto")
    @ResponseBody
    public ResponseEntity<Object> test3(@RequestBody @Validated TestValidatorDto testValidatorDto, BindingResult result){
        log.info("===== test3 called =======");
        log.info("testValidatorDto ={}", testValidatorDto);

        if (result.hasErrors()){
            return ResponseEntity.badRequest().body("Test 실패");
        }
        return ResponseEntity.ok("Test 성공");
    }

    // 파일 업로드 test 2
    @GetMapping("/items/new")
    public String newItem(@ModelAttribute ItemForm form){
        return "test/multipartFIleTest";
    }

    /*@PostMapping("/items/new")
    public String saveItem(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes) throws IOException{

        UploadFile attachFile = fileStore.storeFile(form.getAttachFile());
        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());

        // DB 에 저장
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setAttachFile(attachFile);
        item.setImageFiles(storeImageFiles);
        itemRepository.save(item);

        redirectAttributes.addAttribute("itemId", item.getId());

        return "redirect:/items/{itemId}";
    }*/

    @GetMapping("/items/{id}")
    public String items(@PathVariable Long id, Model model){
        Item item = itemRepository.findById(id);
        model.addAttribute("item", item);
        return "/test/item-view";

    }

    // 파일 업로드 test
    /*@GetMapping("/testMultipartFile")
    public String testMultipartFile(@ModelAttribute ItemForm form){

        return "test/multipartFIleTest";
    }

    @PostMapping("/testMultipartFile")
    public String uploadFormAct(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes) throws IOException {
        log.info("testMultipartFile called.....");

        UploadFile attachFile = fileStore.storeFile(form.getAttachFile());

        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());
        log.info("storeImageFiles = {}", storeImageFiles);

        // DB 에 저장
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setAttachFile(attachFile);
        item.setImageFiles(storeImageFiles);

        redirectAttributes.addFlashAttribute("itemId", item.getId());
        return "redirect:/test/testMultipartFile/{itemId}";
    }

    @GetMapping("/testMultipartFile/{id}")
    public String items(@PathVariable Long id, Model model){
        Item item = itemRepository.findById(id);
        model.addAttribute("item", item);
        return "/test/item-view";
    }*/

    @GetMapping("/layoutTest")
    public String layoutTest(){
        return "test/HtmlLayoutTest";
    }

    @GetMapping("/layoutTest2")
    public String layoutTest2(){
        return "test/htmlLayoutTest2";
    }

    @Autowired
    private ApplicationContext context;

    @GetMapping("/context")
    @ResponseBody
    public String context() {
        log.info("## context");
        log.info("\t > dataSource from getBean = {}", context.getBean(DataSource.class));
        log.info("\t > dataSource from getBeansOfType = {}", context.getBeansOfType(DataSource.class));
        return "success";
    }

    // modal test
    @GetMapping("/modalTest")
    public String modalTest(){
        return "/test/modalTestView";
    }
}
