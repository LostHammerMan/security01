package com.demo.security01.entity.lounge;

import com.demo.security01.entity.study.StudyEntity;
import com.demo.security01.entity.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class BoardLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_IDX")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOUNGE_IDX", nullable = true)
    private LoungeEntity lounge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDY_IDX", nullable = true)
    private StudyEntity study;

    // 라운지 관련
    @Builder
    public BoardLike(User user, LoungeEntity lounge, StudyEntity study) {
        this.user = user;
        this.lounge = lounge;
        this.study = study;
        
    }
    
    // 스터디 관련
	/*
	 * public BoardLike(User user, StudyEntity study) { this.user = user;
	 * this.studyEntity = study; }
	 */
}
