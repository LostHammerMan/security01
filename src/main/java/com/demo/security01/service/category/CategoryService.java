/*
package com.demo.security01.service.category;

import com.demo.security01.model.dto.CategoryDto;
import com.demo.security01.repository.category.CategoryRepository;
import com.querydsl.core.group.GroupBy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto createCategoryRoot(){
        Map<Long, List<CategoryDto>> groupingByParent = categoryRepository.findAll()
                .stream()
                .map(categoryEntity -> new CategoryDto(categoryEntity.getCategoryIdx(), categoryEntity.getCategoryName(), categoryEntity.getParentCategoryIdx()))
                .collect(groupingb)
    }
}
*/
