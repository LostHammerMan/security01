package com.demo.security01.service.category;

import com.demo.security01.model.dto.category.CategoryDto;
import com.demo.security01.repository.category.CategoryRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepositoryCustom categoryRepositoryCustom;


    // CategoryService 에서 findAll 한 결과를 CategoryDto 로 변환하여 List 로 받아 컨트롤러에 전달
    @Transactional
    public List<CategoryDto> getCategoryList(){
        log.info("========== CategoryService =============");
        log.info("\t getCategoryList");

        List<CategoryDto> results = categoryRepositoryCustom.findAllCategory()
                .stream().map(CategoryDto::of).collect(Collectors.toList());
        log.info("\t\t results = {}", results);
        return results;
    }

    @Transactional
    public List<CategoryDto> getSubCategoryList(Long idx){
        log.info("========== CategoryService =============");
        log.info("\t getSubCategoryList");

        List<CategoryDto> subResults = categoryRepositoryCustom.findSubCategory(idx)
                .stream().map(CategoryDto::of).collect(Collectors.toList());
        log.info("subResults = {}", subResults);
        return subResults;

    }
}
