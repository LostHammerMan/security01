package com.demo.security01.model.dto.comment;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Getter @Setter
@Slf4j
@NoArgsConstructor
@ToString
public class CommentRequestDto {

    private String content;
    private Long boardIdx;
    private String username;

    @Builder
    public CommentRequestDto(String content, Long boardIdx) {
        this.content = content;
        this.boardIdx = boardIdx;
    }
}
