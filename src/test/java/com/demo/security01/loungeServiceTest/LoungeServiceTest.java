package com.demo.security01.loungeServiceTest;

import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.community.LoungeModifyRequest;
import com.demo.security01.model.dto.community.LoungeWriteRequest;
import com.demo.security01.model.utils.TickParser_ProfileImg;
import com.demo.security01.repository.category.CategoryRepository;
import com.demo.security01.repository.community.LoungeRepository;
import com.demo.security01.repository.user.UserRepository;
import com.demo.security01.service.community.LoungeService;
import com.demo.security01.service.user.MailServiceImpl;
import com.demo.security01.service.user.UserProfileUploadService;
import com.demo.security01.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.Validator;

import javax.annotation.Resource;

@SpringBootTest
public class LoungeServiceTest {

    @Autowired
    private LoungeService loungeService;

    @Autowired
    private LoungeRepository loungeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserProfileUploadService profileService;

    @Autowired
    private TickParser_ProfileImg tickParserTest;

    @Resource(name = "modUserEmailValidator")
    private Validator modUserEmailValidator;

    @BeforeEach
    void clean(){
        loungeRepository.deleteAll();
    }

    @Test
    @DisplayName("라운지 게시글 저장")
    void test1(){
        // 카테고리
        CategoryEntity findCate = categoryRepository.findById(2L).orElseThrow();

        // 유저
        User findUser = userRepository.findById(1).orElseThrow(() ->
                new UsernameNotFoundException("해당 유저를 찾을 수 없습니다"));

        // 게시글 저장
        LoungeWriteRequest request = LoungeWriteRequest.builder()
                .title("제목1")
                .contents("내용1")
                .cateCode(findCate).build();

        loungeService.loungeSave(request, findUser.getUsername());
    }

    @Test
    @DisplayName("라운지 게시글 수정 - 성공")
    void test2(){
        // 카테고리
        CategoryEntity findCate = categoryRepository.findById(2L).orElseThrow();

        // 유저
        User findUser = userRepository.findById(1).orElseThrow(() ->
                new UsernameNotFoundException("해당 유저를 찾을 수 없습니다"));

        // 게시글 저장
        LoungeWriteRequest request = LoungeWriteRequest.builder()
                .title("제목1")
                .contents("내용1")
                .cateCode(findCate).build();

        loungeService.loungeSave(request, findUser.getUsername());

        LoungeModifyRequest request2 = LoungeModifyRequest.builder()
                .title("변경된 제목")
                .contents("변경된 내용")
                .cateCode(findCate).build();

        loungeService.loungeModify(request2, 4L);
    }

    @Test
    @DisplayName("라운지 게시글 삭제 - 성공")
    public void test3(){
        // 카테고리
        CategoryEntity findCate = categoryRepository.findById(2L).orElseThrow();

        // 유저
        User findUser = userRepository.findById(1).orElseThrow(() ->
                new UsernameNotFoundException("해당 유저를 찾을 수 없습니다"));

        // 게시글 저장
        LoungeWriteRequest request = LoungeWriteRequest.builder()
                .title("제목1")
                .contents("내용1")
                .cateCode(findCate).build();

        loungeService.loungeSave(request, findUser.getUsername());

        loungeService.loungeDelete(5L);
    }
}

