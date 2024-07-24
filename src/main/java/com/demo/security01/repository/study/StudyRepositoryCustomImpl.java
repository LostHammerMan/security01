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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.util.List;

import static com.demo.security01.entity.study.QRecruitPositions.*;
import static com.demo.security01.entity.study.QStudyEntity.*;
import static com.demo.security01.entity.study.QStudy_Positions.*;
import static com.demo.security01.entity.tag.QSkillTagEntity.*;
import static com.demo.security01.entity.tag.QStudySkillTagEntity.*;

@RequiredArgsConstructor
@Slf4j
public class StudyRepositoryCustomImpl implements StudyRepositoryCustom{

    private final JPAQueryFactory queryFactory;



    // 스터디 목록 + 페이징
    @Override
    public List<StudyEntity> getStudyList(StudyCriteria criteria, Pageable pageable){
//    public List<StudyEntity> getStudyList(StudyCriteria criteria, Pageable pageable){

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
                .offset(pageable.getOffset()) // 페이지 번호
                .limit(pageable.getPageSize()) // 페이지 사이즈
                .fetch();

            return entities;
//        return PageableExecutionUtils.getPage(entities, pageable, count);
    }

    // count 쿼리
    @Override
    public Long getStudyListCount(StudyCriteria criteria) {
        Long count = queryFactory.select(studyEntity.count())
                .from(studyEntity)
                .leftJoin(studySkillTagEntity).on(studySkillTagEntity.study.eq(studyEntity))
                .leftJoin(skillTagEntity).on(studySkillTagEntity.skillTag.eq(skillTagEntity))
                .leftJoin(study_Positions).on(study_Positions.studyEntity.eq(studyEntity))
                .leftJoin(recruitPositions).on(study_Positions.positions.eq(recruitPositions))
//                .where(studyEntity.studySkillTagEntity)
                .where(
                        skillIdxEq(criteria.getSkillIdx()),
                        positionIdxEq(criteria.getPositionIdx()),
                        isNotFin(criteria.getIsFin())
                )
                .fetchOne();
        return count;
    }

    // 페이징 최적화
    public Page<StudyEntity> getStudyPageComplex(StudyCriteria criteria, Pageable pageable){
        log.info("========= StudyRepositoryCustomImpl ============");
        log.info("\t\t getStudyPageComplex called...");
        List<StudyEntity> contents = getStudyList(criteria, pageable);
        Long count = getStudyListCount(criteria);

        return new PageImpl<>(contents, pageable, count);
    }

    // 마감날짜 도달시 update
    @Override
    public void updateIsFin() {
        List<Long> studyIdx = queryFactory
                .select(studyEntity.idx)
                .from(studyEntity)
                .where(studyEntity.recruitDeadline.lt(LocalDate.now())
                        .and(studyEntity.isFIn.eq(0)))
                .fetch();

        // tuple 읽기 전용 - 값 수정 불가, 결국 엔티티 불러와야 함
        // Tuple : 테이블의 row, 쿼리 결과로 나온 결과 행들을 타입에 맞춰 담을 수 있는 인터페이스
        for (long result : studyIdx){
            queryFactory
                    .update(studyEntity)
                    .set(studyEntity.isFIn, 1)
                    .where(studyEntity.idx.eq(result))
                    .execute();
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

    // 마감여부별
    private BooleanExpression isNotFin(Integer isFin){
        return studyEntity.isFIn.eq(isFin);
    }
}
