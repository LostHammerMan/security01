package com.demo.security01.model.dto.comment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ModifyCommentResponseDto {

    private Long id;
    private String content;
    private LocalDateTime updateDate;

    @Builder
    public ModifyCommentResponseDto(Long id, String content) {
        this.id = id;
        this.content = content;
        this.updateDate = LocalDateTime.now();
    }
}
