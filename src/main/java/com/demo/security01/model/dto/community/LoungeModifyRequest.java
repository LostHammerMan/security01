package com.demo.security01.model.dto.community;

import com.demo.security01.entity.CategoryEntity;
import lombok.Builder;
import lombok.Data;

@Data
public class LoungeModifyRequest {

    private CategoryEntity cateCode;
    private String title;
    private String contents;

    @Builder
    public LoungeModifyRequest(CategoryEntity cateCode, String title, String contents) {
        this.cateCode = cateCode;
        this.title = title;
        this.contents = contents;
    }
}
