package com.demo.security01.repository.study;

import com.demo.security01.entity.study.StudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<StudyEntity, Long>, StudyRepositoryCustom {
}
