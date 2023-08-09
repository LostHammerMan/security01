package com.demo.security01.repository.category;

import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.model.dto.category.CategoryDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.demo.security01.entity.QCategoryEntity.categoryEntity;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;



    @Override
    public List<CategoryEntity> findAllCategory() {
        List<CategoryEntity> result = queryFactory
                .selectFrom(categoryEntity)
                // parentCategory == null 인 경우 최상위 카테고리
                .where(categoryEntity.parentCategory.isNull())
                .fetch();
        return result;
        /*public List<Category> findAll() {
            return em.createQuery("select c from Category c where c.parent is NULL", Category.class).getResultList();
        }*/
    }

    // 하위 카테고리
    @Override
    public List<CategoryEntity> findSubCategory(Long idx) {
        return queryFactory
                .selectFrom(categoryEntity)
                .where(categoryEntity.categoryIdx.eq(idx))
                .fetch();
    }
}
