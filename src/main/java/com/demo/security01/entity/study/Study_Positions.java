package com.demo.security01.entity.study;

import lombok.Builder;

import javax.persistence.*;
import java.security.Principal;

@Table(name = "STUDY_POSITION")
@Entity
public class Study_Positions {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "STUDY_IDX")
    private StudyEntity studyEntity;

    @ManyToOne
    @JoinColumn(name = "POSITIONS_IDX")
    private RecruitPositions positions;

    @Builder
    public Study_Positions(StudyEntity studyEntity, RecruitPositions positions) {
        this.studyEntity = studyEntity;
        this.positions = positions;
    }
}
