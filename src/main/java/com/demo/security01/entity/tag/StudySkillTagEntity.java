package com.demo.security01.entity.tag;

import com.demo.security01.entity.study.StudyEntity;

import javax.persistence.*;

@Entity
@Table(name = "STUDY_SKILLTAG")
public class StudySkillTagEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDY_SKILL_IDX")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "STUDY_IDX")
    private StudyEntity study;

    @ManyToOne
    @JoinColumn(name = "SKILL_TAG_IDX")
    private SKillTagEntity skillTag;
}