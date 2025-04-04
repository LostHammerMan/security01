package com.demo.security01.repository.study;

import static com.demo.security01.entity.QCategoryEntity.categoryEntity;
import static com.demo.security01.entity.lounge.QBoardLike.boardLike;
import static com.demo.security01.entity.study.QRecruitPositions.recruitPositions;
import static com.demo.security01.entity.study.QStudyEntity.studyEntity;
import static com.demo.security01.entity.study.QStudy_Positions.study_Positions;
import static com.demo.security01.entity.tag.QSkillTagEntity.skillTagEntity;
import static com.demo.security01.entity.tag.QStudySkillTagEntity.studySkillTagEntity;
import static com.demo.security01.entity.tag.QUser_Skilltag.user_Skilltag;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.demo.security01.entity.study.StudyEntity;
import com.demo.security01.entity.tag.QSkillTagEntity;
import com.demo.security01.entity.tag.User_Skilltag;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.study.request.StudyCriteria;
import com.demo.security01.model.dto.study.response.StudyResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class StudyRepositoryCustomImpl implements StudyRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    // 인기 스터디 top4
    // 연관된 엔티티의 특정 필드에 접근하려면 join 을 사용해 필요한 필드 매핑
    @Override
	public List<StudyResponseDto> getStudyListTop4(int limitNum) {
		return queryFactory.select(Projections.fields(StudyResponseDto.class, 
				studyEntity.idx.as("studyIdx"),
				studyEntity.title,
				studyEntity.contents,
				studyEntity.recruitDeadline,
				studyEntity.viewCount,
				categoryEntity.categoryName
				))
				.from(studyEntity)
				.join(studyEntity.category, categoryEntity)
//				.where(studyEntity.isFIn.eq(0))
				.orderBy(studyEntity.likeCount.desc(), studyEntity.viewCount.desc(), studyEntity.regDate.desc())
				.limit(limitNum)
				.fetch();
	}
    
    // 추천 스터디
    @Override
	public List<StudyResponseDto> getRecommendStudy(User user) {
    	return queryFactory
    	.selectDistinct(Projections.fields(StudyResponseDto.class, 
    			studyEntity.idx.as("studyIdx"),
				studyEntity.title,
				studyEntity.contents,
				studyEntity.recruitDeadline,
				studyEntity.likeCount,
				studyEntity.viewCount,
				studyEntity.regDate,
				categoryEntity.categoryName
    			))
    	.from(studyEntity)
    	.join(studySkillTagEntity).on(studyEntity.eq(studySkillTagEntity.study))
    	.join(skillTagEntity).on(studySkillTagEntity.skillTag.eq(skillTagEntity))
    	.join(user_Skilltag).on(skillTagEntity.eq(user_Skilltag.skillTag))
//    	.join(studyEntity.category, categoryEntity)
    	.join(categoryEntity).on(studyEntity.category.eq(categoryEntity))
    	.where(user_Skilltag.user.eq(user))
    	.orderBy(studyEntity.likeCount.desc(), studyEntity.viewCount.desc(), studyEntity.regDate.desc())
    	.limit(16)
    	.fetch();
	}
    
    // 스터디 목록 + 페이징
    @Override
    public List<StudyEntity> getStudyList(StudyCriteria criteria, Pageable pageable, User user){
//    public List<StudyEntity> getStudyList(StudyCriteria criteria, Pageable pageable){

        List<StudyEntity> entities = queryFactory
                .selectFrom(studyEntity)
                .distinct()
                .leftJoin(studySkillTagEntity).on(studySkillTagEntity.study.eq(studyEntity))
                .leftJoin(skillTagEntity).on(studySkillTagEntity.skillTag.eq(skillTagEntity))
                .leftJoin(study_Positions).on(study_Positions.studyEntity.eq(studyEntity))
                .leftJoin(recruitPositions).on(study_Positions.positions.eq(recruitPositions))
                .leftJoin(boardLike).on(boardLike.study.eq(studyEntity))
//                .where(studyEntity.studySkillTagEntity)
                .where(
                		processEq(criteria.getProcess()),
                		categoryEq(criteria.getCategoryIdx()),
                        skillIdxEq(criteria.getSkillIdx()),
                        positionIdxEq(criteria.getPositionIdx()),
                        isNotFin(criteria.getIsFin()),
                        searchWithTitleAndContents(criteria.getKeyword()),
                        checkStudyLike(user)
                )
                .orderBy(studyEntity.regDate.desc())
                .offset(pageable.getOffset()) // 페이지 번호
                .limit(pageable.getPageSize()) // 페이지 사이즈
                .fetch();

            return entities;
//        return PageableExecutionUtils.getPage(entities, pageable, count);
    }

    // count 쿼리 
    // study 개수만 구하면 되는데 왜 left join?
    @Override
    public Long getStudyListCount(StudyCriteria criteria, User user) {
        Long count = queryFactory.select(studyEntity.idx.countDistinct())
                .from(studyEntity)
//                .leftJoin(studySkillTagEntity).on(studySkillTagEntity.study.eq(studyEntity))
//                .leftJoin(skillTagEntity).on(studySkillTagEntity.skillTag.eq(skillTagEntity))
//                .leftJoin(study_Positions).on(study_Positions.studyEntity.eq(studyEntity))
//                .leftJoin(recruitPositions).on(study_Positions.positions.eq(recruitPositions))
                .leftJoin(boardLike).on(boardLike.study.eq(studyEntity))
//                .where(studyEntity.studySkillTagEntity)
                .where(
                		processEq(criteria.getProcess()),
                		categoryEq(criteria.getCategoryIdx()),
                        skillIdxEq(criteria.getSkillIdx()),
                        positionIdxEq(criteria.getPositionIdx()),
                        isNotFin(criteria.getIsFin()),
                        searchWithTitleAndContents(criteria.getKeyword()),
                        checkStudyLike(user)
                )
                .fetchOne();
        return count;
    }

    // 페이징 최적화
    public Page<StudyEntity> getStudyPageComplex(StudyCriteria criteria, Pageable pageable, User user){
        log.info("========= StudyRepositoryCustomImpl ============");
        log.info("\t\t getStudyPageComplex called...");
        List<StudyEntity> contents = getStudyList(criteria, pageable, user);
        Long totalCount = getStudyListCount(criteria, user);

        return new PageImpl<>(contents, pageable, totalCount);
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
    // 카테고리별  
    private BooleanExpression categoryEq(Long categoryIdx) {
    	if(categoryIdx == null) return null;
    	return studyEntity.category.categoryIdx.eq(categoryIdx);
    }
    
    // 진행방식별
    private BooleanExpression processEq(String process) {
    	if(!StringUtils.hasText(process)) return null;
    	return studyEntity.progressMethod.eq(process);
    }
    
    // 기술 스택별
    private BooleanExpression skillIdxEq(List<Long> skillIdx){
        if (skillIdx == null) return null;
        return studyEntity.studySkillTagEntity.any().skillTag.idx.in(skillIdx);
    }

    // 모집 분야별
    private BooleanExpression positionIdxEq(List<Long> positionIdx){
        if (positionIdx == null) return null;
        return studyEntity.study_positions.any().positions.idx.in(positionIdx);
    }

    // 마감여부별
    private BooleanExpression isNotFin(Integer isFin){
        if (isFin == null) return null;
        return studyEntity.isFIn.eq(isFin);
    }
    
    // 제목 + 내용 검색
    private BooleanBuilder searchWithTitleAndContents(String keyword) {
    	BooleanBuilder builder = new BooleanBuilder();
    	return builder
    			.or(titleLike(keyword))
    			.or(contentLike(keyword));
    }
    
    // 제목 검색
    private BooleanExpression titleLike(String keyword) {
    	if (!StringUtils.hasText(keyword)) return null;
    	return studyEntity.title.contains(keyword);
    }
    
    // 내용 검색
    private BooleanExpression contentLike(String keyword) {
    	if (!StringUtils.hasText(keyword)) return null;
    	return studyEntity.contents.contains(keyword);
    }
    
    // 좋아요한 경우
    private BooleanExpression checkStudyLike(User loginUser) {
    	if(loginUser == null) return null;
    	return boardLike.user.eq(loginUser);
    }

    // 마감여부 따른 리스트조회(테스트용)
    public List<StudyEntity> getListByIsFin(StudyCriteria criteria){
        List<StudyResponseDto> responseDtoList = new ArrayList<>();

        List<StudyEntity> findStudyList = queryFactory
                .selectFrom(studyEntity)
                .where(isNotFin(criteria.getIsFin()))
                .fetch();
        return findStudyList;
    }

	
}
