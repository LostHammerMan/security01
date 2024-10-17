package com.demo.security01.model.dto.comment.request;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import com.demo.security01.model.BoardType;

@Getter @Setter
@Slf4j
@NoArgsConstructor
@ToString
public class CommentRequestDto {

    private String content;
    private Long boardIdx;
    private String username;
    private BoardType boardType;

    @Builder
    public CommentRequestDto(String content, Long boardIdx, BoardType boardType) {
        this.content = content;
        this.boardIdx = boardIdx;
        this.boardType = boardType;
    }
}
