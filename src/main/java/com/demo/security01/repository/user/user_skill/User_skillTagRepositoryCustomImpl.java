package com.demo.security01.repository.user.user_skill;

import static com.demo.security01.entity.QCategoryEntity.categoryEntity;
import static com.demo.security01.entity.study.QStudyEntity.studyEntity;
import static com.demo.security01.entity.tag.QUser_Skilltag.user_Skilltag;

import java.util.List;

import com.demo.security01.entity.QCategoryEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.study.response.StudyResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class User_skillTagRepositoryCustomImpl implements User_skillTagRepositoryCustom {

	private final JPAQueryFactory qf;
	
	// 유저_스킬 테이블 삭제
	@Override
	public void user_skillTagDeleteByUserIdx(User user) {
		// TODO Auto-generated method stub
		qf.delete(user_Skilltag)
			.where(user_Skilltag.user.eq(user))
			.execute();
	}

	// 추천 스터디 조회 -> 스터디 쪽으로 옮기는 게 맞을듯?
	@Override
	public List<StudyResponseDto> getRecommendStudy(User loginUser) {
//		// TODO Auto-generated method stub
//		qf.selectDistinct(Projections.fields(StudyResponseDto.class, 
//				studyEntity.idx.as("studyIdx"),
//				studyEntity.title,
//				studyEntity.recruitDeadline,
//				studyEntity.viewCount,
//				categoryEntity.categoryName
//				))
//		.from(studyEntity)
//		.join(studyEntity.category, QCategoryEntity.categoryEntity)
//		.join(user_Skilltag.skillTag).on(studyEntity.studySkillTagEntity)
//		
//		.fetch();
		
		return null;
	}
	
	
	
	

}
