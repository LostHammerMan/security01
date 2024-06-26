package com.demo.security01.entity.hashtag;


import com.demo.security01.entity.study.StudyEntity;

import javax.persistence.*;

@Entity
@Table(name = "SKILL_TAG")
public class SKillTagEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HASHTAG_IDX")
    private Long idx;

    @Column(name = "TAG_NAME")
    private String tagName;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_IDX")
    private StudyEntity study;
}
