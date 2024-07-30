package com.demo.security01.repository.study;

import com.demo.security01.entity.study.StudyEntity;
import com.demo.security01.model.dto.study.request.StudyCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudyRepositoryCustom {
//    List<StudyEntity> getStudyList(List<Long> skillIdx, List<Long> positionIdx);
    List<StudyEntity> getStudyList(StudyCriteria criteria, Pageable pageable);

    // studyList count 쿼리
    Long getStudyListCount(StudyCriteria criteria);

    // 페이징 최적화 버전
    Page<StudyEntity> getStudyPageComplex(StudyCriteria criteria, Pageable pageable);
    // 스터디가 마감날짜에 도달
    void updateIsFin();

    // 마감여부 따른 리스트조회(테스트용)
    public List<StudyEntity> getListByIsFin(StudyCriteria criteria);
}
