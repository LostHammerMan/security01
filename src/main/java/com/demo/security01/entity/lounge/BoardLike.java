package com.demo.security01.entity.lounge;

import com.demo.security01.entity.study.StudyEntity;
import com.demo.security01.entity.user.User;
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
    private StudyEntity studyEntity;

    public BoardLike(User user, LoungeEntity lounge) {
        this.user = user;
        this.lounge = lounge;
    }
}
