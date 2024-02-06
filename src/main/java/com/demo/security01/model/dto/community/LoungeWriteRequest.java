package com.demo.security01.model.dto.community;

import com.demo.security01.entity.CategoryEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoungeWriteRequest {

    private CategoryEntity cateCode;
    private String title;
    private String contents;

    @Builder
    public LoungeWriteRequest(CategoryEntity cateCode, String title, String contents) {
        this.cateCode = cateCode;
        this.title = title;
        this.contents = contents;
    }
}
