package com.demo.security01.model.dto.category;

import com.demo.security01.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CategoryDto {

    private Long categoryIdx;
    private String categoryName;
    private Integer depth;
    private String categoryPath;
    private List<CategoryDto> subCategory;

    // Entity 로 가져온 결과를 그대로 전해주면 안됨.
    // DTO 클래스를 만들어 CategoryEntity 를 DTO 로 변환
    public static CategoryDto of(CategoryEntity categoryEntity){
        return new CategoryDto(
                categoryEntity.getCategoryIdx(),
                categoryEntity.getCategoryName(),
                categoryEntity.getDepth(),
                categoryEntity.getCategoryPath(),
                categoryEntity.getSubCategory().stream().map(CategoryDto::of)
                        .collect(Collectors.toList())
        );
    }
}
