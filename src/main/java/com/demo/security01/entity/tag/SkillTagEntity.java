package com.demo.security01.entity.tag;


import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "SKILLTAG")
public class SkillTagEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SKILL_TAG_IDX")
    private Long idx;

    @Column(name = "TAG_NAME")
    private String tagName;
    
    @Column(name = "SKILL_IMG_NAME")
    private String skillImgName;

    @OneToMany(mappedBy = "skillTag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudySkillTagEntity> studySkillTagEntity;

    public String getTagName() {
        return tagName;
    }

    public Long getIdx() {
        return idx;
    }

	public String getSkillImgName() {
		return skillImgName;
	}

    
}
