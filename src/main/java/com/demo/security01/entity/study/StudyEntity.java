package com.demo.security01.entity.study;

import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.entity.user.User;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Table(name = "STUDY")
public class StudyEntity {

    /*
    * @Size , @Length , @Column(length = )차이
    * - @Size : Bean Validation, JPA 와 독립적, 유효성 검사 O
    * - @Length : 유효성 검사 O
    * - @Column(length = ) : entity 로 db 생성시 사용
    * */

    @Column(name = "STUDY_IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_IDX")
    private CategoryEntity category;

    @Column(name = "RECRUITED_NUMBER")
    private Integer recruitedNumber;

    @Column(name = "PROGRESS_METHOD")
    private String progressMethod; // 온라인, 오프라인 ---

    @Column(name = "PROGRESS_PERIOD")
    private String progressPeriod;

    @Column(name = "RECRUIT_DEADLINE")
    private LocalDate recruitDeadline;

    @Column(name = "RECRUIT_POSITION")
    private String recruitPosition;

    @Column(name = "CONTACT_METHOD")
    private String contactMethod;

    @Column(name = "CONTACT_ADDRESS")
    private String contactAddress;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENTS", length = 5000)
    private String contents;

    @Column(name = "REG_DATE")
    private LocalDateTime regDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @ManyToOne
    @JoinColumn(name = "USER_IDX")
    private User user;


}