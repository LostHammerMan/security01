package com.demo.security01.model.dto.comment.response;

import com.demo.security01.entity.user.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class CommentResponseDto {

    private String content;
    private LocalDateTime regDate;
//    private User username;
    private String username;
    private String profileFilePath;
    @Builder
    public CommentResponseDto(String content, LocalDateTime regDate, String username, String filePath) {
        this.content = content;
        this.regDate = regDate;
        this.username = username;
        this.profileFilePath = filePath;
    }
}
