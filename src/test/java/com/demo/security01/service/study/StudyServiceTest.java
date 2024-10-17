package com.demo.security01.service.study;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.demo.security01.model.BoardType;
import com.demo.security01.model.dto.comment.request.CommentRequestDto;
import com.demo.security01.model.dto.study.request.StudyCriteria;
import com.demo.security01.model.dto.study.request.StudyModifyRequestDto;
import com.demo.security01.model.dto.study.request.StudyRequestDto;
import com.demo.security01.model.dto.study.response.StudyResponseDto;
import com.demo.security01.repository.study.StudyRepository;
import com.demo.security01.repository.study.study_positions.Study_PositionsRepository;
import com.demo.security01.repository.study.study_skill.Study_SkillTagRepository;
import com.demo.security01.service.community.CommentService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class StudyServiceTest {

    @Autowired
    private StudyService studyService;

    @Autowired
    private Study_SkillTagRepository study_skillTagRepository;

    @Autowired
    private Study_PositionsRepository study_positionsRepository;

    @Autowired
    private StudyRepository studyRepository;
    
    @Autowired
    private CommentService commentService;


    @Test
    @DisplayName("스터디 저장 성공")
    void studyGenerateSuccess(){

//        studyService.studyDeleteAll();


        Set<Long> positionIdx = new HashSet<>();
        positionIdx.add(1L);
        positionIdx.add(2L);

        List<Long> tagIdx = new ArrayList<>();
//        tagIdx.add(3L);
//        tagIdx.add(4L);
        tagIdx.add(1L);
        tagIdx.add(2L);
        tagIdx.add(3L);

        // category code : 7 - 프로젝트, 8 - 스터디
        for (int i=0; i < 200; i++){
            StudyRequestDto studyRequestDto = StudyRequestDto.builder()
                    .cateCode(5L)
                    .title(i +"번째 스터디 모집합니다.")
                    .contents("열심히 하실분")
                    .contactMethod("핸드폰")
                    .contactAddress("010-000-0000")
                    .progressMethod("온라인")
                    .progressPeriod("6개월")
                    .recruitDeadline("2024-10-09")
                    .recruitedNumber(6)
                    .recruitPositions(positionIdx)
                    .skillTagIdx(tagIdx)
                    .build();

            studyService.studyGenerate(studyRequestDto, "admin");
        }

    }

    @Test
    @DisplayName("스터디 수정 성공")
    void modifySuccessTest(){


        Set<Long> positionIdx = new HashSet<>();
        positionIdx.add(1L);
//        positionIdx.add(2L);

        List<Long> tagIdx = new ArrayList<>();
        tagIdx.add(5L);
//        tagIdx.add(2L);

        StudyModifyRequestDto requestDto = StudyModifyRequestDto.builder()
                .cateCode(7L)
                .title("수정 제목111")
                .contents("수정-열심히 하실분")
                .contactMethod("수정-핸드폰")
                .contactAddress("수정-010-000-0000")
                .progressMethod("수정-온라인")
                .progressPeriod("수정-6개월")
                .recruitDeadline(LocalDate.now())
                .recruitedNumber(5)
                .recruitPositions(positionIdx)
                .skillTagIdx(tagIdx)
                .build();

        studyService.studyModify(6L, requestDto, "admin");
    }

    // 스터디 삭제 - 성공
    @Test
    @DisplayName("스터디 삭제 - 성공")
    void studyDeleteTest(){

        studyService.studyDelete(1L, "admin");
    }

    // 스터디/프로젝트 단건 조회
    @Test
    @DisplayName("단건 조회 - 성공")
    void getStudy(){
        Long idx = 4L;

        StudyResponseDto response = studyService.getStudy(idx);
        log.info("response = {}", response.toString());
    }

    // 스터디 리스트 불러오기
    @Test
    @DisplayName("스터디 리스트 - 성공")
    void getStudyList(){
        Pageable pageable = PageRequest.of(0, 9);

        StudyCriteria criteria = new StudyCriteria();

//        List<Long> skillIndexes = new ArrayList<>();
//        skillIndexes.add(5L);
//
//        List<Long> positions = new ArrayList<>();
//        positions.add(2L);

//        criteria.setSkillIdx(skillIndexes);
//        criteria.setPositionIdx(positions);

//        log.info("criteria = {}", criteria);

//        studyService.getStudyList(indexes);
//        for (StudyResponseDto study : studyService.getStudyList(criteria, pageable)){
//            log.info("studyList = {}", study);
//        }

        for (StudyResponseDto study : studyService.getStudyList(criteria, pageable)){
            log.info("studyList = {}", study);
        }
    }


    // 스터디_스킬 태그 삭제 test(repository)
    // @Transaction 이 없는 repository 에서 바로 호출시
    // TransactionRequiredException 발생
    @Test
    @DisplayName("스터디_스킬 태그 삭제 test()")
    void Study_skillTagDeleteTest(){
        Long idx = 5L;
        studyService.deleteByStudyIdx(idx);
    }

    @Test
    @DisplayName("스터디_포지션 삭제 test")
    void Study_PosiontDeleteTest(){
        Long idx = 5L;
        studyService.study_positionsDeleteByStudyIdx(idx);
    }

    //
    @Test
    @DisplayName("isFIn update 전 조회")
    void updateIsFIn(){
        studyRepository.updateIsFin();
    }
    
    // 스터디 댓글 등록 테스트
    @Test
    @DisplayName("스터디 댓글 등록 - 성공")
    void addCommentTest() {
    	
    	CommentRequestDto request = CommentRequestDto.builder()
    			
    			.boardIdx(379L)
    			.content("되냐")
    			.boardType(BoardType.STUDY)
    			.build();
    	
    	request.setUsername("admin");
    	
    	commentService.addComment(request);
    }

}