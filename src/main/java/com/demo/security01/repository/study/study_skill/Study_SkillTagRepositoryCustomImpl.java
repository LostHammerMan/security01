package com.demo.security01.repository.study.study_skill;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.demo.security01.entity.tag.QStudySkillTagEntity.*;

@RequiredArgsConstructor
public class Study_SkillTagRepositoryCustomImpl implements Study_SkillTagRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    // 스터디_스킬태그 삭제(해당하는 studyIdx)
    @Override
    public void deleteByStudyIdx(Long studyIdx){
        long count = queryFactory.delete(studySkillTagEntity)
                .where(studySkillTagEntity.study.idx.eq(studyIdx))
                .execute();

    }


}
