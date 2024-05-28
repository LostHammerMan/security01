package com.demo.security01.entity.lounge;

import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.entity.comment.CommentEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.community.LoungeModifyRequest;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LOUNGE_ENTITY")
public class LoungeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOUNGE_IDX")
    private Long idx;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "COUNT")
    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_IDX")
    private User user;

    @OneToOne
    @JoinColumn(name = "CATE_CODE")
    private CategoryEntity cateCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "REG_DATE")
    private LocalDateTime regDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "LIKE_COUNT")
    private Integer likeCount = 0;

    @Column(name = "VIEW_COUNT")
    private Integer viewCount = 0;

    @OneToMany(mappedBy = "lounge", cascade = CascadeType.ALL)
    private List<CommentEntity> commentEntity;

    public void loungeEdit(LoungeModifyRequest request){
        this.title  = request.getTitle() != null? request.getTitle() : this.getTitle() ;
        this.content = request.getContents() != null ? request.getContents() : this.getContent();
        this.cateCode = request.getCateCode() != null  ? request.getCateCode() : this.getCateCode();
    }

    // 좋아요
    public void setLikeCount(int num){
        this.likeCount = num;
    }

    public void setViewCount(int num){
        this.viewCount = num;
    }

    @Builder
    public LoungeEntity(String title, String content, int count, User user, CategoryEntity cateCode, LocalDateTime regDate) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.cateCode = cateCode;
        this.regDate = LocalDateTime.now();
    }
}
