package com.demo.security01.entity.study;

import javax.persistence.*;

@Entity
@Table(name = "RECRUIT_POSITIONS")
public class RecruitPositions {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSITIONS_IDX")
    private Long idx;

    @Column(name = "POSITION_NAME", unique = true)
    private String positionName;



}
