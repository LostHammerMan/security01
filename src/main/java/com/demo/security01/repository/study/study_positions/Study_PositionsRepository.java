package com.demo.security01.repository.study.study_positions;

import com.demo.security01.entity.study.Study_Positions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Study_PositionsRepository extends JpaRepository<Study_Positions, Long>, Study_positionsRepositoryCustom {
}
