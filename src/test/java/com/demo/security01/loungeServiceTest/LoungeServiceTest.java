package com.demo.security01.loungeServiceTest;

import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.SortOrder;
import com.demo.security01.model.dto.community.LoungeCriteria;
import com.demo.security01.model.dto.community.LoungeListResponseDto;
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
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
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

//    @BeforeEach
//    void clean(){
//        loungeRepository.deleteAll();
//    }

    @Test
    @DisplayName("라운지 게시글 저장")
    void test1(){
        // 카테고리
//        CategoryEntity findCate = categoryRepository.findById(2L).orElseThrow();


        for (int i=0; i <50; i++){
            Long cateCode = 4L;
            // 유저
            User findUser = userRepository.findById(2).orElseThrow(() ->
                    new UsernameNotFoundException("해당 유저를 찾을 수 없습니다"));

            // 게시글 저장
            LoungeWriteRequest request = LoungeWriteRequest.builder()
                    .title("제목" + i)
                    .contents("내용" + i)
                    .cateCode(cateCode).build();

            loungeService.loungeSave(request, findUser.getUsername());
        }
    }

    @Test
    @DisplayName("라운지 게시글 수정 - 성공")
    void test2(){

        String username = "admin";

        User findUser = userRepository.findByUsername(username).orElseThrow();

        LoungeModifyRequest request = LoungeModifyRequest.builder()
                .loungeId(374L)
                .cateCode(6L)
                .title("수정333")
                .contents("수정333")
                .build();

        loungeService.loungeModify(request, findUser.getUsername());
//        LoungeModifyRequest request2 = LoungeModifyRequest.builder()
//                .title("변경된 제목")
//                .contents("변경된 내용")
//                .cateCode(cateCode).build();
        LoungeEntity findLounge = loungeRepository.findById(374L)
                .orElseThrow(() -> new RuntimeException());

            log.info("findLounge.title = {}", findLounge.getTitle());
            log.info("findLounge.contents = {}", findLounge.getTitle());
    }

    @Test
    @DisplayName("라운지 게시글 삭제 - 성공")
    public void deleteTest(){
        // 카테고리
        CategoryEntity findCate = categoryRepository.findById(2L).orElseThrow();

        // 유저
//        User findUser = userRepository.findById(1).orElseThrow(() ->
//                new UsernameNotFoundException("해당 유저를 찾을 수 없습니다"));
        User findUser = userRepository.findByUsername("admin").orElseThrow();


        loungeService.loungeDelete(342L, findUser.getUsername());
    }

    // 라운지 게시글 - 페이징
    @Test
    @DisplayName("라운지 게시글 - 페이징")
    public void getLoungeListWithPaging(){

//        // 카테고리
//        CategoryEntity findCate = categoryRepository.findById(2L).orElseThrow();
//
//        // 유저
//        User findUser = userRepository.findById(1).orElseThrow(() ->
//                new UsernameNotFoundException("해당 유저를 찾을 수 없습니다"));
//        for (int i = 0; i <= 300; i++){
//
//            // 게시글 저장
//            LoungeWriteRequest request = LoungeWriteRequest.builder()
//                    .title("제목" + i)
//                    .contents("내용" + i)
//                    .cateCode(findCate).build();
//
//            loungeService.loungeSave(request, findUser.getUsername());
//        }

//        List<LoungeEntity> list = loungeRepository.getAllLoungeWithPaging(null, 10);
//        List<LoungeEntity> list = loungeService.getAllLoungeWithPaging(null);
        Pageable pageable = PageRequest.of(0, 9);
        Slice<LoungeEntity> list = loungeService.getAllLoungeWithPagingWithSlice(null, pageable);

        for (LoungeEntity lounge : list){
            System.out.println("lounge.id = " + lounge.getIdx());
        }

//        Assertions.assertEquals(9, list.size());
    }

    @Test
    @DisplayName("2번째 페이지")
    public void getLoungeListWithPaging2thPage(){
        Long maxIdx = loungeRepository.getMaxLoungeIdx();

//        List<LoungeEntity> list = loungeService.getAllLoungeWithPaging(maxIdx -10L);
//        for (LoungeEntity lounge : list){
//            System.out.println("lounge.id = " + lounge.getIdx());
//        }
        Pageable pageable = PageRequest.of(0, 9); // no offset -> 페이지는 항상 0 으로 고정
        Slice<LoungeEntity> list = loungeService.getAllLoungeWithPagingWithSlice(null, pageable);

        // given
        Long first = list.getContent().get(0).getIdx();
        Long last = list.getContent().get(8).getIdx();


        for (LoungeEntity lounge : list){
            System.out.println("lounge.id = " + lounge.getIdx());
        }

//        Assertions.assertEquals(9, list.size());
    }

    @Test
    @DisplayName("3번째 페이지")
    public void getLoungeListWithPaging3thPage(){
        Long maxIdx = loungeRepository.getMaxLoungeIdx();

        List<LoungeListResponseDto> list = loungeService.getAllLoungeWithPaging(maxIdx -20L);
        for (LoungeListResponseDto lounge : list){
            System.out.println("lounge.id = " + lounge.getIdx());
        }

        Assertions.assertEquals(9, list.size());
    }
    
    
    // 라운지 정렬조건 추가
    @Test
    @DisplayName("라운지 조회 - 정렬조건 추가 - 성공")
    public void getLoungeListWithOrder() {
    	Long maxIdx = loungeRepository.getMaxLoungeIdx();
    	
    	LoungeCriteria cri = new LoungeCriteria();
//    	cri.setOrder(SortOrder.LIKE);
    	cri.setOrder(SortOrder.LIKE);
//    	cri.setOrder(SortOrder.VIEW);
    	
    	List<LoungeListResponseDto> results = loungeService.getAllLoungeWithPaging(maxIdx, cri);
    	
    	for(LoungeListResponseDto result : results) {
    		System.out.println("result = " + result);
    	}
    	
    }
}



