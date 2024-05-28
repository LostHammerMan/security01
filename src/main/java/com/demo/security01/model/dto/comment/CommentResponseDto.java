package com.demo.security01.model.dto.comment;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class CommentResponseDto {

    private String content;
    private LocalDateTime regDate;

    @Builder
    public CommentResponseDto(String content, LocalDateTime regDate) {
        this.content = content;
        this.regDate = regDate;
    }
}
