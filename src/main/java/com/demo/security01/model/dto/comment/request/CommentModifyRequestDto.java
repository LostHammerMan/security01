package com.demo.security01.model.dto.comment.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentModifyRequestDto {

//    private Long id;
    private String content;
    private String commenter;
    private LocalDateTime updateDate;

    @Builder
    public CommentModifyRequestDto(Long id, String content, String commenter) {
//        this.id = id;
        this.content = content;
        this.commenter = commenter;
        this.updateDate = LocalDateTime.now();
    }
}
