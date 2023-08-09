package com.demo.security01;

import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.model.dto.category.CategoryDto;
import com.demo.security01.repository.category.CategoryRepository;
import com.demo.security01.repository.category.CategoryRepositoryCustomImpl;
import com.demo.security01.service.category.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CategoryTest {

    @Autowired
    private CategoryRepositoryCustomImpl categoryRepositoryCustomImpl;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void getCategoryList(){
        log.info("======== category Test ==============");
        List<CategoryDto> categoryResult =categoryService.getCategoryList();
//        List<CategoryEntity> categoryResult = categoryRepositoryCustomImpl.findAllCategory();
        log.info("\t categoryResult = {}", categoryResult);
        /*for (int i=0 ; i < categoryResult.size(); i++){
            String name = categoryResult.get(i++).getCategoryName();
            log.info("name = {}", name);
        }*/

        for (CategoryDto cateAA : categoryResult){
            log.info("CategoryName = {}", cateAA.getCategoryName());
            List<CategoryDto> subCategory = cateAA.getSubCategory();

            for (CategoryDto subCategory2 : subCategory){
                String subCategoryName = subCategory2.getCategoryName();
                log.info("subCategoryName = {}", subCategoryName);
            }

        }
    }

    @Test
    public void getSubCategoryTest(){
        log.info("============== getSubCategoryTest ============");
//        categoryDto.setCategoryIdx(2L);
//        Long idx = 2L;
//        categoryService.getSubCategoryList(idx);
        List<CategoryDto> categoryResult =categoryService.getCategoryList();

        for (CategoryDto categoryDto : categoryResult){
            log.info("categoryDto = {}", categoryDto);

            if (categoryDto.getCategoryIdx() == 2L){
                List<CategoryDto> subCategoryDto = categoryDto.getSubCategory();
                log.info("subCategoryDto = {}", subCategoryDto);

                for (CategoryDto subSubCategoryDto : subCategoryDto){
                    log.info("subCategoryName = {}",subSubCategoryDto.getCategoryName());
                }
            }
        }

    }

    @Test
    public void test(){
        String results = "옘병";
        log.info("results = {}", results);
    }




}
