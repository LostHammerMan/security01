package com.demo.security01.controller.test;

import com.demo.security01.model.test.MultipartFileTest.FileStore;
import com.demo.security01.model.test.MultipartFileTest.Item;
import com.demo.security01.model.test.MultipartFileTest.ItemForm;
import com.demo.security01.model.test.MultipartFileTest.UploadFile;
import com.demo.security01.repository.test.MultipartFileTest.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/profileTest")
public class MultipartFileTestController {

    private final ItemRepository itemRepository;
    private final FileStore fileStore;

    @GetMapping("/profileTest2")
    public String profileTest2(){
        return "test/item-form2";
    }

    @PostMapping("/profileAjaxUpload")
    @ResponseBody
//    public ResponseEntity<Object> profileAjaxUpload(@R
//    equestParam("profileImg") MultipartFile profileImg){
    // 기본적으로 @ModelAttribute
    // JSON 으로 전송시 @RequestParam or @RequestBody
    public ResponseEntity<Object> profileAjaxUpload(ItemForm form) throws IOException {
        log.info("====== profileAjaxUpload ==========");
        log.info("multipartFile = {}", form);


        UploadFile imageFile = fileStore.storeFile(form.getProfileImg());

        // 저장
        Item item = new Item();
        item.setProfileImg(imageFile);
        itemRepository.save(item);

        log.info("item = {}", item);

        log.info("\t imageFile = {}", imageFile);

        return ResponseEntity.ok("전송완료");
    }
}
