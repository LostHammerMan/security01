package com.demo.security01.entity.study;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "RECRUIT_POSITIONS")
@NoArgsConstructor
public class RecruitPositions {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POSITIONS_IDX")
    private Long idx;

    @Column(name = "POSITION_NAME", unique = true)
    private String positionName;

    public String getPositionName() {
        return positionName;
    }
}
