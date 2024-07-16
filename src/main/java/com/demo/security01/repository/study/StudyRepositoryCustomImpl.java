package com.demo.security01.repository.study;

import com.demo.security01.entity.study.QStudyEntity;
import com.demo.security01.entity.study.StudyEntity;
import com.demo.security01.entity.tag.QStudySkillTagEntity;
import com.demo.security01.entity.tag.StudySkillTagEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.demo.security01.entity.study.QStudyEntity.*;

@RequiredArgsConstructor
public class StudyRepositoryCustomImpl implements StudyRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    // 스터디 목록 + 페이징
    public List<StudyEntity> getStudyList(){
        List<StudyEntity> entities = queryFactory
                .selectDistinct(studyEntity)
//                .where(studyEntity.studySkillTagEntity)
                .orderBy(studyEntity.regDate.desc())
                .fetch();

        return entities;
    }

    // 정렬 조건
    // 기술 스택별
    private BooleanExpression skillIdxEq(Long skillIdx){
        QStudySkillTagEntity studySkillTagEntity = QStudySkillTagEntity.studySkillTagEntity;

        return null;
    }
}
