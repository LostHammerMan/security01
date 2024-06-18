package com.demo.security01.model.dto.community;

import com.demo.security01.entity.CategoryEntity;
import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
public class LoungeWriteRequest {

    private Long cateCode;
    private String title;
    private String contents;

    @Builder
    public LoungeWriteRequest(Long cateCode, String title, String contents) {
        this.cateCode = cateCode;
        this.title = title;
        this.contents = contents;
    }
}
