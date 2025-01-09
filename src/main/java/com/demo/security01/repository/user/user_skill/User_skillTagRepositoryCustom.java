package com.demo.security01.repository.user.user_skill;

import java.util.List;

import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.study.response.StudyResponseDto;

public interface User_skillTagRepositoryCustom {
	void user_skillTagDeleteByUserIdx(User user);
	
	List<StudyResponseDto> getRecommendStudy(User user);
}
