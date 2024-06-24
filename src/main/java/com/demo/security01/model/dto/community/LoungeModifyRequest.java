package com.demo.security01.model.dto.community;

import com.demo.security01.entity.CategoryEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoungeModifyRequest {
    private Long loungeId;
    private Long cateCode;
    private String title;
    private String contents;
    private LocalDateTime updateDate;

    @Builder
    public LoungeModifyRequest(Long loungeId, Long cateCode, String title, String contents) {
        this.loungeId = loungeId;
        this.cateCode = cateCode;
        this.title = title;
        this.contents = contents;
        this.updateDate = LocalDateTime.now();
    }
}
