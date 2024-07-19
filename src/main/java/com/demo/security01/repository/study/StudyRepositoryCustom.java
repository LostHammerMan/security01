package com.demo.security01.repository.study;

import com.demo.security01.entity.study.StudyEntity;
import com.demo.security01.model.dto.study.request.StudyCriteria;

import java.util.List;

public interface StudyRepositoryCustom {
//    List<StudyEntity> getStudyList(List<Long> skillIdx, List<Long> positionIdx);
    List<StudyEntity> getStudyList(StudyCriteria criteria);

    // 스터디가 마감날짜에 도달
    void updateIsFin();
}
