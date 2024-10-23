package com.demo.security01.entity.study;

import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.entity.comment.CommentEntity;
import com.demo.security01.entity.lounge.BoardLike;
import com.demo.security01.entity.tag.StudySkillTagEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.study.request.StudyModifyRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "STUDY")
@Getter
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

    @OneToMany(mappedBy = "studyEntity",cascade = CascadeType.ALL)
    private Set<Study_Positions> study_positions;

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

    // orphanRemoval = true : 고아 객체가 된 경우, 자동으로 삭제
    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudySkillTagEntity> studySkillTagEntity;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<BoardLike> boardLikes;

    @Column(name = "IS_FIN")
    private Integer isFIn;
    
    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<CommentEntity> commentEntities;

    @Column(name = "LIKE_COUNT", columnDefinition = "integer default 0")
    private Integer likeCount = 0;
    
    @Column(name = "VIEW_COUNT", columnDefinition = "integer default 0")
    private Integer viewCount = 0;
    
    
    
//    public User getUser() {
//        return user;
//    }

    @Builder
    public StudyEntity(Long idx, CategoryEntity category, Integer recruitedNumber, String progressMethod, String progressPeriod, LocalDate recruitDeadline, String contactMethod, String contactAddress, String title, String contents, User user, Integer isFIn) {
        this.idx = idx;
        this.category = category;
        this.recruitedNumber = recruitedNumber;
        this.progressMethod = progressMethod;
        this.progressPeriod = progressPeriod;
        this.recruitDeadline = recruitDeadline;
        this.contactMethod = contactMethod;
        this.contactAddress = contactAddress;
        this.title = title;
        this.contents = contents;
        this.regDate = LocalDateTime.now();
        this.user = user;
        this.isFIn = 0;
    }

    // 스터디 수정
    public void studyEdit(StudyModifyRequestDto requestDto, CategoryEntity category){
        this.category = category;
        this.recruitedNumber = requestDto.getRecruitedNumber() != null ? requestDto.getRecruitedNumber() : this.recruitedNumber;
        this.progressMethod = requestDto.getProgressMethod() != null ? requestDto.getProgressMethod() : this.progressMethod;
        this.progressPeriod = requestDto.getProgressPeriod() != null ? requestDto.getProgressMethod() : this.progressPeriod;
        this.recruitDeadline = requestDto.getRecruitDeadline() != null ? requestDto.getRecruitDeadline() : this.recruitDeadline;
        this.contactMethod = requestDto.getContactMethod() != null ? requestDto.getContactMethod() : this.contactMethod;
        this.contactAddress = requestDto.getContactAddress() != null ? requestDto.getContactAddress() : this.contactAddress;
        this.title = requestDto.getTitle() != null ? requestDto.getTitle() : this.title;
        this.contents = requestDto.getContents() != null ? requestDto.getContents() : this.contents;
        this.updateDate = LocalDateTime.now();
    }

    public void setIsFIn(Integer isFIn) {
        this.isFIn = isFIn;
    }

    @Override
    public String toString() {
        return "StudyEntity{" +
                "idx=" + idx +
                ", category=" + category +
                ", recruitedNumber=" + recruitedNumber +
                ", progressMethod='" + progressMethod + '\'' +
                ", progressPeriod='" + progressPeriod + '\'' +
                ", recruitDeadline=" + recruitDeadline +
                ", study_positions=" + study_positions +
                ", contactMethod='" + contactMethod + '\'' +
                ", contactAddress='" + contactAddress + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                ", user=" + user +
                ", studySkillTagEntity=" + studySkillTagEntity +
                '}';
    }
    

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	public void setLikeCount(int num) {
		this.likeCount = num;
	}
}
