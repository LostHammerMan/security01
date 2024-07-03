package com.demo.security01.entity.tag;


import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "SKILLTAG")
public class SKillTagEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SKILL_TAG_IDX")
    private Long idx;

    @Column(name = "TAG_NAME")
    private String tagName;

}
