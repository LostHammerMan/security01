package com.demo.security01.entity.tag;

import com.demo.security01.entity.study.StudyEntity;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "STUDY_SKILLTAG")
@NoArgsConstructor
public class StudySkillTagEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDY_SKILL_IDX")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "STUDY_IDX")
    private StudyEntity study;

    @ManyToOne
    @JoinColumn(name = "SKILL_TAG_IDX")
    private SkillTagEntity skillTag;

    public String getSkillTagName() {
        return skillTag.getTagName();
    }

    public Long getSkillTagIdx(){
        return skillTag.getIdx();
    }

    @Builder
    public StudySkillTagEntity(StudyEntity study, SkillTagEntity skillTag) {
        this.study = study;
        this.skillTag = skillTag;
    }
}
