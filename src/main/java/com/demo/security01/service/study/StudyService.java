package com.demo.security01.service.study;

import com.demo.security01.config.exception.UserNotMatchException;
import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.entity.study.RecruitPositions;
import com.demo.security01.entity.study.StudyEntity;
import com.demo.security01.entity.study.Study_Positions;
import com.demo.security01.entity.tag.SkillTagEntity;
import com.demo.security01.entity.tag.StudySkillTagEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.study.request.StudyCriteria;
import com.demo.security01.model.dto.study.request.StudyModifyRequestDto;
import com.demo.security01.model.dto.study.request.StudyRequestDto;
import com.demo.security01.model.dto.study.response.StudyResponseDto;
import com.demo.security01.repository.category.CategoryRepository;
import com.demo.security01.repository.study.*;
import com.demo.security01.repository.study.study_positions.RecruitPositionsRepository;
import com.demo.security01.repository.study.study_positions.Study_PositionsRepository;
import com.demo.security01.repository.study.study_skill.SkillTagRepository;
import com.demo.security01.repository.study.study_skill.Study_SkillTagRepository;
import com.demo.security01.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
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

        StudyEntity studyEntity = StudyEntity.builder()
                .category(findCategory)
                .user(findUser)
                .title(requestDto.getTitle())
                .contents(requestDto.getContents())
                .contactAddress(requestDto.getContactAddress())
                .contactMethod(requestDto.getContactMethod())
                .progressPeriod(requestDto.getProgressPeriod())
                .recruitDeadline(requestDto.getRecruitDeadline())
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
    public StudyResponseDto getStudy(Long studyIdx){
        StudyEntity findStudy = studyRepository.findById(studyIdx)
                .orElseThrow(
                        () -> new EntityNotFoundException("해당 스터디/프로젝트는 존재하지 않습니다")
                );


        // 스킬 태그
        Set<String> skillTagNames = new HashSet<>();
        for (StudySkillTagEntity skillTag: findStudy.getStudySkillTagEntity()){
            skillTagNames.add(skillTag.getSkillTagName());
        }

        // 포지션
        Set<String> positionNames = new HashSet<>();
        for (Study_Positions study_position : findStudy.getStudy_positions()){
            positionNames.add(study_position.getPositions().getPositionName());
        }

        // entity -> dto
        StudyResponseDto responseDto = StudyResponseDto.builder()
                .categoryName(findStudy.getCategory().getCategoryName())
                .title(findStudy.getTitle())
                .contents(findStudy.getContents())
                .contactMethod(findStudy.getContactMethod())
                .contactAddress(findStudy.getContactAddress())
                .progressPeriod(findStudy.getProgressPeriod())
                .recruitDeadline(findStudy.getRecruitDeadline())
                .recruitedNumber(findStudy.getRecruitedNumber())
                .skillTags(skillTagNames)
                .recruitPositions(positionNames)
                .isFin(findStudy.getIsFIn()).build();

        return responseDto;
    }

    // 목록 조회 + 페이징(no-offset)
    public List<StudyResponseDto> getStudyList(StudyCriteria criteria){

        List<StudyResponseDto> responseDtoList = new ArrayList<>();
        for (StudyEntity findStudy : studyRepository.getStudyList(criteria)){
            // 스킬 태그
            Set<String> skillTagNames = new HashSet<>();
            for (StudySkillTagEntity skillTag: findStudy.getStudySkillTagEntity()){
                skillTagNames.add(skillTag.getSkillTagName());
            }

            // 포지션
            Set<String> positionNames = new HashSet<>();
            for (Study_Positions study_position : findStudy.getStudy_positions()){
                positionNames.add(study_position.getPositions().getPositionName());
            }

            StudyResponseDto responseDto = StudyResponseDto.builder()
                    .categoryName(findStudy.getCategory().getCategoryName())
                    .title(findStudy.getTitle())
                    .contents(findStudy.getContents())
                    .contactMethod(findStudy.getContactMethod())
                    .contactAddress(findStudy.getContactAddress())
                    .progressPeriod(findStudy.getProgressPeriod())
                    .recruitDeadline(findStudy.getRecruitDeadline())
                    .recruitedNumber(findStudy.getRecruitedNumber())
                    .skillTags(skillTagNames)
                    .recruitPositions(positionNames)
                    .isFin(findStudy.getIsFIn()).build();

            responseDtoList.add(responseDto);
        }
        return responseDtoList;
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


}