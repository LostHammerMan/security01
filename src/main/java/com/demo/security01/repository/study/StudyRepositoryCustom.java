package com.demo.security01.repository.study;

import com.demo.security01.entity.study.StudyEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.study.request.StudyCriteria;
import com.demo.security01.model.dto.study.response.StudyResponseDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudyRepositoryCustom {
//    List<StudyEntity> getStudyList(StudyCriteria criteria, Pageable pageable);
    List<StudyEntity> getStudyList(StudyCriteria criteria, Pageable pageable, User user);

    // studyList count 쿼리
//    Long getStudyListCount(StudyCriteria criteria);
    Long getStudyListCount(StudyCriteria criteria, User user);

    // 페이징 최적화 버전
//    Page<StudyEntity> getStudyPageComplex(StudyCriteria criteria, Pageable pageable);
    Page<StudyEntity> getStudyPageComplex(StudyCriteria criteria, Pageable pageable, User user);
    // 스터디가 마감날짜에 도달
    void updateIsFin();

    // 마감여부 따른 리스트조회(테스트용)
    public List<StudyEntity> getListByIsFin(StudyCriteria criteria);
    
    // 인기 스터디 top4
    public List<StudyResponseDto> getStudyListTop4();
    
    // 추천 스터디 
    public List<StudyResponseDto> getRecommendStudy(User user);
}
