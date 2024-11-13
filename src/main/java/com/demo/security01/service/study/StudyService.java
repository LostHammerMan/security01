package com.demo.security01.service.study;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.security01.config.exception.UserNotMatchException;
import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.entity.study.RecruitPositions;
import com.demo.security01.entity.study.StudyEntity;
import com.demo.security01.entity.study.Study_Positions;
import com.demo.security01.entity.tag.SkillTagEntity;
import com.demo.security01.entity.tag.StudySkillTagEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.BoardType;
import com.demo.security01.model.dto.paging.PageResponseDto;
import com.demo.security01.model.dto.study.request.StudyCriteria;
import com.demo.security01.model.dto.study.request.StudyModifyRequestDto;
import com.demo.security01.model.dto.study.request.StudyRequestDto;
import com.demo.security01.model.dto.study.response.StudyResponseDto;
import com.demo.security01.repository.boardLike.LikeRepository;
import com.demo.security01.repository.category.CategoryRepository;
import com.demo.security01.repository.comment.CommentRepository;
import com.demo.security01.repository.study.StudyRepository;
import com.demo.security01.repository.study.study_positions.RecruitPositionsRepository;
import com.demo.security01.repository.study.study_positions.Study_PositionsRepository;
import com.demo.security01.repository.study.study_skill.SkillTagRepository;
import com.demo.security01.repository.study.study_skill.Study_SkillTagRepository;
import com.demo.security01.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final RecruitPositionsRepository recruitPositionsRepository;
    private final Study_PositionsRepository study_positionsRepository;
    private final SkillTagRepository skillTagRepository;
    private final Study_SkillTagRepository study_skillTagRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    // 스터디 만들기
    @Transactional
    public void studyGenerate(StudyRequestDto requestDto, String username){

        User findUser = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username + " 은 존재하지 않는 회원입니다.")
        );

        CategoryEntity findCategory = categoryRepository.findById(requestDto.getCateCode())
                .orElseThrow(
                        () -> new EntityNotFoundException("해당 카테고리는 존재하지 않습니다")
                );

        // String -> LocalDate
        LocalDate endDate = LocalDate.parse(requestDto.getRecruitDeadline(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        StudyEntity studyEntity = StudyEntity.builder()
                .category(findCategory)
                .user(findUser)
                .title(requestDto.getTitle())
                .contents(requestDto.getContents())
                .contactAddress(requestDto.getContactAddress())
                .contactMethod(requestDto.getContactMethod())
                .progressPeriod(requestDto.getProgressPeriod())
                .recruitDeadline(endDate)
                .recruitedNumber(requestDto.getRecruitedNumber())
                .progressMethod(requestDto.getProgressMethod())
                .isFIn(0)
                .build();

        studyRepository.save(studyEntity);

        // 모집 포지션
        if (requestDto.getRecruitPositions() != null){
            for (Long recruitPositionIdx : requestDto.getRecruitPositions()){
                RecruitPositions recruitPositions = recruitPositionsRepository.findById(recruitPositionIdx)
                        .orElseThrow(
                                () -> new EntityNotFoundException("해당 포지션은 존재하지 않습니다.")
                        );

                Study_Positions study_positions = Study_Positions.builder()
                        .positions(recruitPositions)
                        .studyEntity(studyEntity).build();

                study_positionsRepository.save(study_positions);
            }
        }

        // 기술 스택 입력한 경우
        if (requestDto.getSkillTagIdx() != null){
            for (Long skillIdx : requestDto.getSkillTagIdx()){
                SkillTagEntity skillTag = skillTagRepository.findById(skillIdx)
                        .orElseThrow(
                                () -> new EntityNotFoundException("해당 기술 스택은 존재하지 않습니다")
                        );

                StudySkillTagEntity studySkillTagEntity = StudySkillTagEntity.builder()
                        .skillTag(skillTag)
                        .study(studyEntity).build();

                study_skillTagRepository.save(studySkillTagEntity);
            }
        }
    }

    // 스터디 수정
    @Transactional
    public void studyModify(Long studyIdx, StudyModifyRequestDto requestDto, String username){

        User loginUser = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(username + "님은 존재하지 않는 유저입니다.")
                );

        StudyEntity findStudy = studyRepository.findById(studyIdx)
                .orElseThrow(
                        () -> new EntityNotFoundException("해당 스터디/프로젝트는 존재하지 않습니다.")
                );

        if (!username.equals(findStudy.getUser().getUsername())){
            throw new UserNotMatchException();
        }

        CategoryEntity findCategory = categoryRepository.findById(requestDto.getCateCode())
                .orElseThrow(
                        () -> new EntityNotFoundException("해당 카테고리는 존재하지 않습니다")
                );

        // 모집 포지션 수정한 경우
        if (requestDto.getRecruitPositions() != null){

            study_positionsRepository.study_positionsDeleteByStudyIdx(studyIdx);

            for (Long recruitPositionIdx : requestDto.getRecruitPositions()){
                RecruitPositions recruitPositions = recruitPositionsRepository.findById(recruitPositionIdx)
                        .orElseThrow(
                                () -> new EntityNotFoundException("해당 포지션은 존재하지 않습니다.")
                        );

                Study_Positions study_positions = Study_Positions.builder()
                        .positions(recruitPositions)
                        .studyEntity(findStudy).build();
            	
                
                study_positionsRepository.save(study_positions);
            }
        }

        // 기술 스택 수정한 경우
        if (requestDto.getSkillTagIdx() != null){

            // 기존 등록된 스터디_스킬 태그 삭제
            study_skillTagRepository.deleteByStudyIdx(studyIdx);

            for (Long skillIdx : requestDto.getSkillTagIdx()){
                SkillTagEntity skillTag = skillTagRepository.findById(skillIdx)
                        .orElseThrow(
                                () -> new EntityNotFoundException("해당 기술 스택은 존재하지 않습니다")
                        );

                StudySkillTagEntity studySkillTagEntity = StudySkillTagEntity.builder()
                        .skillTag(skillTag)
                        .study(findStudy).build();

                study_skillTagRepository.save(studySkillTagEntity);
            }
        }

        findStudy.studyEdit(requestDto, findCategory);
    }

    // 스터디 삭제
    @Transactional
    public void studyDelete(Long studyIdx, String username){

        StudyEntity findStudy = studyRepository.findById(studyIdx)
                        .orElseThrow(
                                () -> new EntityNotFoundException("해당 스터디/프로젝트는 존재하지 않습니다")
                        );

        User loginUser = userRepository.findByUsername(username)
                        .orElseThrow(
                                () -> new UserNotMatchException()
                        );

        studyRepository.deleteById(studyIdx);
    }

    // 단건 조회
    @Transactional
    public StudyResponseDto getStudy(Long studyIdx){
        StudyEntity findStudy = studyRepository.findById(studyIdx)
                .orElseThrow(
                        () -> new EntityNotFoundException("해당 스터디/프로젝트는 존재하지 않습니다")
                );
        

        // 스킬 태그
//        Set<String> skillTagNames = new HashSet<>();
        List<String> skillTagNames = new ArrayList<String>();
        for (StudySkillTagEntity skillTag: findStudy.getStudySkillTagEntity()){
//            skillTagNames.add(skillTag.getSkillTagName());
            skillTagNames.add(skillTag.getStudyImgName());
            log.info("skillTag.getStudyImgName() = ", skillTag.getStudyImgName());
        }

        // 포지션
        List<String> positionNames = new ArrayList<String>();
        for (Study_Positions study_position : findStudy.getStudy_positions()){
            positionNames.add(study_position.getPositions().getPositionName());
        }
        
        findStudy.setViewCount(findStudy.getViewCount() + 1);

        // 댓글 수 
        
        
        // entity -> dto
        StudyResponseDto responseDto = StudyResponseDto.builder()
        		.studyIdx(findStudy.getIdx())
                .categoryName(findStudy.getCategory().getCategoryName())
                .username(findStudy.getUser().getUsername())
                .userProfileImgName(findStudy.getUser().getUserProfile().getFileName())
                .title(findStudy.getTitle())
                .contents(findStudy.getContents())
//                .regDate(findStudy.getRegDate())
                .regDate(findStudy.getRegDate())
                .contactMethod(findStudy.getContactMethod())
                .contactAddress(findStudy.getContactAddress())
                .progressPeriod(findStudy.getProgressPeriod())
                .process(findStudy.getProgressMethod())
                .recruitDeadline(findStudy.getRecruitDeadline())
                .recruitedNumber(findStudy.getRecruitedNumber())
                .viewCount(findStudy.getViewCount())
                
                .likeCount(findStudy.getLikeCount())
                .skillTags(skillTagNames)
                .recruitPositions(positionNames)
                .isFin(findStudy.getIsFIn()).build();

        log.info("responseDto = {}", responseDto);
        return responseDto;
    }

    // 목록 조회 + 페이징
    public PageResponseDto<StudyResponseDto> getStudyList(StudyCriteria criteria, Pageable pageable, String username){
//    	public List<StudyResponseDto> getStudyList(StudyCriteria criteria, Pageable pageable){
        log.info("======== StudyService ============");
        log.info("\t\t getStudyList called.....");
        
        User loginUser = userRepository.findByUsername(username).orElseThrow(
        		() -> new EntityNotFoundException("해당 유저는 존재하지 않음")
        		);
        
        Integer loginUserIdx = loginUser.getId();
        
        Page<StudyEntity> studyList= studyRepository.getStudyPageComplex(criteria, pageable, loginUserIdx);

        List<StudyResponseDto> responseDtoList = new ArrayList<>();
//        for (StudyEntity findStudy : studyRepository.getStudyList(criteria, pageable)){
//        for (StudyEntity findStudy : studyRepository.getStudyPageComplex(criteria, pageable)){
    	for (StudyEntity findStudy : studyList){
            // 스킬 태그
            List<String> skillTagNames = new ArrayList<String>();
            for (StudySkillTagEntity skillTag: findStudy.getStudySkillTagEntity()){
//                skillTagNames.add(skillTag.getStudyImgName());
                skillTagNames.add(skillTag.getStudyImgName());
            }

            // 포지션
//            Set<String> postionNames = new HashSet<>();
            List<String> positionNames = new ArrayList<String>();
            for (Study_Positions study_position : findStudy.getStudy_positions()){
//            	positionNames.add(study_position.getPositions().getPositionName());
            	positionNames.add(study_position.getPostsionName());
            }
            
            // 
            

            StudyResponseDto responseDto = StudyResponseDto.builder()
            		.studyIdx(findStudy.getIdx())
                    .categoryName(findStudy.getCategory().getCategoryName())
                    .title(findStudy.getTitle())
                    .contents(findStudy.getContents())
                    .contactMethod(findStudy.getContactMethod())
                    .contactAddress(findStudy.getContactAddress())
                    .progressPeriod(findStudy.getProgressPeriod())
                    .process(findStudy.getProgressMethod())
                    .recruitDeadline(findStudy.getRecruitDeadline())
                    .recruitedNumber(findStudy.getRecruitedNumber())
                    .skillTags(skillTagNames)
                    .recruitPositions(positionNames)
                    .regDate(findStudy.getRegDate())
                    .username(findStudy.getUser().getUsername())
                    .viewCount(findStudy.getViewCount())
                    .likeCount(findStudy.getLikeCount())
                    .commentCount(commentRepository.getCommentListCount(findStudy.getIdx(), BoardType.STUDY))
                    .isFin(findStudy.getIsFIn()).build();

            responseDtoList.add(responseDto);
        }
        
        // 제네릭 클래스로 빌더를 만든경우, builder 앞에 제네릭 타입 들어감 주의!
        PageResponseDto<StudyResponseDto> result = PageResponseDto.<StudyResponseDto>builder()
        		.dtoList(responseDtoList)
        		.pageable(studyList.getPageable())
        		.totalCount(studyList.getTotalElements())
        		.build();
        
        
        return result;
    }
    
    // 좋아요 체크되어 있는지 여부
    public boolean isCheckLike(Long studyIdx, String username) {
    	
    	boolean isLikeCheck = false;
    	
    	User findUser = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("해당 유저 없음")
        );
    	
    	StudyEntity findStudy = studyRepository.findById(studyIdx).orElseThrow(
    			() -> new EntityNotFoundException("해당 스터디는 존재하지 않음")
		);
    	
    	if(likeRepository.existsByUserAndStudy(findUser, findStudy)) {
    		return isLikeCheck = true;
    	}else {
    		return isLikeCheck;
    	}
    	
    }

   
    public List<StudyResponseDto> getListByIsFin(StudyCriteria criteria){
        List<StudyResponseDto> dtos = new ArrayList<>();

        List<StudyEntity> list = studyRepository.getListByIsFin(criteria);
        for (StudyEntity result : list){
            StudyResponseDto dto = StudyResponseDto.builder()
                    .title(result.getTitle())
                    .contents(result.getContents())
                    .isFin(result.getIsFIn()).build();

            dtos.add(dto);
        }
        return dtos;
    }


    // 현재날짜가 마감일자를 넘긴 경우 -> isFin = 1
    @Transactional
    public void updateIsFin(){
        log.info("====== StudyService called.... =======");
        log.info("\t\tupdateIsFin call");
        studyRepository.updateIsFin();
    }
    
    // top4 스터디 조회
    public List<StudyResponseDto> getStudyListTop4(){
    	return studyRepository.getStudyListTop4();
    }


    // 스터디_ 스킬 태그 삭제 (테스트용)
    @Transactional
    public void deleteByStudyIdx(Long idx){
        study_skillTagRepository.deleteByStudyIdx(idx);
    }

    // 스터디_포지션 삭제(테스트용)
    @Transactional
    public void study_positionsDeleteByStudyIdx(Long studyIdx){
        study_positionsRepository.study_positionsDeleteByStudyIdx(studyIdx);
    }

    // 전체 삭제
    @Transactional
    public void studyDeleteAll(){
        studyRepository.deleteAll();
    }


}
