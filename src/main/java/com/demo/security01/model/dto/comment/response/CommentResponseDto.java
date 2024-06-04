package com.demo.security01.model.dto.comment.response;

import com.demo.security01.entity.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class CommentResponseDto {

    private String content;
    private Long commentId;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime regDate;
//    private User username;
    private String username;
    private String profileFilePath;
    @Builder
    public CommentResponseDto(Long id, String content, LocalDateTime regDate, String username, String filePath) {
        this.commentId = id;
        this.content = content;
        this.regDate = regDate;
        this.username = username;
        this.profileFilePath = filePath;
    }
}
