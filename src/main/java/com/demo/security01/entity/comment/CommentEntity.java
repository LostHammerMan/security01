package com.demo.security01.entity.comment;

import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.comment.request.CommentModifyRequestDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@ToString(exclude = "lounge")
public class CommentEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_IDX")
    private Long id;

    @Column(name = "COMMENT_CONTENT")
    private String content;

    @Column(name = "REG_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime regDate;

    @Column(name = "UPDATE_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOUNGE_IDX")
    private LoungeEntity lounge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_IDX")
    private User user;

    @Builder
    public CommentEntity(String content, LocalDateTime regDate, LoungeEntity lounge, User user) {
        this.content = content;
        this.regDate = LocalDateTime.now();
        this.lounge = lounge;
        this.user = user;
    }

    // 댓글 수정
    public void modifyComment(CommentModifyRequestDto request){
        this.content = request.getContent();
        this.regDate = request.getUpdateDate();
    }


    public String getContent(){
        return content;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public User getUser(){
        return user;
    }

    public Long getId(){
        return id;
    }
}
