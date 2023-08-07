package com.demo.security01;

import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.model.dto.CategoryDto;
import com.demo.security01.repository.category.CategoryRepositoryCustom;
import com.demo.security01.repository.category.CategoryRepositoryCustomImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
public class CategoryTest {

    @Autowired
    private CategoryRepositoryCustomImpl categoryRepositoryCustomImpl;

    @Test
    public void getCategoryList(){
        List<CategoryEntity> results = categoryRepositoryCustomImpl.findAll();
        /*List<CategoryDto> results = categoryRepositoryCustom.findAll().stream()
                .map(CategoryDto::of).collect(Collectors.toList());*/
        log.info("results = {}", results.toString());
    }

    @Test
    public void test(){
        String results = "옘병";
        log.info("results = {}", results);
    }




}
