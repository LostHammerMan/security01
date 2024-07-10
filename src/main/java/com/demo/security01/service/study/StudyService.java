package com.demo.security01.service.study;

import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.entity.study.RecruitPositions;
import com.demo.security01.entity.study.StudyEntity;
import com.demo.security01.entity.study.Study_Positions;
import com.demo.security01.entity.tag.SkillTagEntity;
import com.demo.security01.entity.tag.StudySkillTagEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.study.request.StudyRequestDto;
import com.demo.security01.model.dto.study.response.StudyResponseDto;
import com.demo.security01.repository.category.CategoryRepository;
import com.demo.security01.repository.study.*;
import com.demo.security01.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
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
    private void studyGenerate(StudyRequestDto requestDto, String username){

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
}
