package com.demo.security01.model.dto.community;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class LoungeDto {

    private String categoryName;
    private String title;
    private String contents;

}
