package com.demo.security01.model.dto;

import com.demo.security01.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long categoryIdx;
    private String categoryName;
    private Long depth;
    private List<CategoryDto> subCategory;

    public static CategoryDto of(CategoryEntity categoryEntity){
        return new CategoryDto(
                categoryEntity.getCategoryIdx(),
                categoryEntity.getCategoryName(),
                categoryEntity.getDepth(),
                categoryEntity.getSubCategory()
                        .stream().map(CategoryDto::of)
                        .collect(Collectors.toList())
        );
    }

}
