package com.demo.security01.repository.study.study_skill;

import com.demo.security01.entity.tag.StudySkillTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Study_SkillTagRepository extends JpaRepository<StudySkillTagEntity, Long>, Study_SkillTagRepositoryCustom {
}
