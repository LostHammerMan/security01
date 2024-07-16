package com.demo.security01.repository.study.study_positions;

import com.demo.security01.entity.study.QStudy_Positions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.demo.security01.entity.study.QStudy_Positions.*;


@RequiredArgsConstructor
public class Study_positionsRepositoryCustomImpl implements Study_positionsRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public void study_positionsDeleteByStudyIdx(Long studyIdx) {
        queryFactory.delete(study_Positions)
                .where(study_Positions.studyEntity.idx.eq(studyIdx))
                .execute();
    }
}
