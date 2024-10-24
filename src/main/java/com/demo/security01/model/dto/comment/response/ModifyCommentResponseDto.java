package com.demo.security01.model.dto.comment.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
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
