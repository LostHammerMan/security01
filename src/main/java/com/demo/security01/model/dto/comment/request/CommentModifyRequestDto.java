package com.demo.security01.model.dto.comment.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentModifyRequestDto {

    private Long id;
    private String content;
    private LocalDateTime updateDate;

    @Builder
    public CommentModifyRequestDto(Long id, String content) {
        this.id = id;
        this.content = content;
        this.updateDate = LocalDateTime.now();
    }
}
