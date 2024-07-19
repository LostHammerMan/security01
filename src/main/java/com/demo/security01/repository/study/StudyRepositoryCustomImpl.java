package com.demo.security01.repository.study;

import com.demo.security01.entity.study.QRecruitPositions;
import com.demo.security01.entity.study.QStudyEntity;
import com.demo.security01.entity.study.QStudy_Positions;
import com.demo.security01.entity.study.StudyEntity;
import com.demo.security01.entity.tag.QSkillTagEntity;
import com.demo.security01.entity.tag.QStudySkillTagEntity;
import com.demo.security01.entity.tag.StudySkillTagEntity;
import com.demo.security01.model.dto.study.request.StudyCriteria;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.demo.security01.entity.study.QRecruitPositions.*;
import static com.demo.security01.entity.study.QStudyEntity.*;
import static com.demo.security01.entity.study.QStudy_Positions.*;
import static com.demo.security01.entity.tag.QSkillTagEntity.*;
import static com.demo.security01.entity.tag.QStudySkillTagEntity.*;

@RequiredArgsConstructor
public class StudyRepositoryCustomImpl implements StudyRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    // 스터디 목록 + 페이징
    @Override
    public List<StudyEntity> getStudyList(StudyCriteria criteria){


        List<StudyEntity> entities = queryFactory
                .selectFrom(studyEntity)
                .distinct()
                .leftJoin(studySkillTagEntity).on(studySkillTagEntity.study.eq(studyEntity))
                .leftJoin(skillTagEntity).on(studySkillTagEntity.skillTag.eq(skillTagEntity))
                .leftJoin(study_Positions).on(study_Positions.studyEntity.eq(studyEntity))
                .leftJoin(recruitPositions).on(study_Positions.positions.eq(recruitPositions))
//                .where(studyEntity.studySkillTagEntity)
                .where(
                        skillIdxEq(criteria.getSkillIdx()),
                        positionIdxEq(criteria.getPositionIdx())
                )
                .orderBy(studyEntity.regDate.desc())
                .fetch();

        return entities;
    }

    // 마감날짜 도달


    @Override
    public void updateIsFin() {
        List<Tuple> studyEntities = queryFactory
                .select(studyEntity.idx,studyEntity.isFIn)
                .from(studyEntity)
                .where(studyEntity.recruitDeadline.lt(LocalDate.now())
                        .and(studyEntity.isFIn.eq(0)))
                .fetch();

        for (Tuple tuple : studyEntities){
            tuple.get(studyEntity.isFIn);
            System.out.println("tuple = " + tuple);
        }

    }

    ///* 정렬 조건 */
    // 기술 스택별
    private BooleanExpression skillIdxEq(List<Long> skillIdx){
        return studyEntity.studySkillTagEntity.any().skillTag.idx.in(skillIdx);
    }

    // 모집 분야별
    private BooleanExpression positionIdxEq(List<Long> positionIdx){
        return studyEntity.study_positions.any().positions.idx.in(positionIdx);
    }

    // 아직 모집중인 모집보기
    private BooleanExpression isNotFin(Integer isFin){
        return studyEntity.isFIn.eq(0);
    }
}
