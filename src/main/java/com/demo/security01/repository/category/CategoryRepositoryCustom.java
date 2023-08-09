package com.demo.security01.repository.category;

import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.model.dto.category.CategoryDto;

import java.util.List;

public interface CategoryRepositoryCustom {

    // 최상위 카테고리
    public List<CategoryEntity> findAllCategory();

    // 하위 카테고리
    public List<CategoryEntity> findSubCategory(Long idx);
}
