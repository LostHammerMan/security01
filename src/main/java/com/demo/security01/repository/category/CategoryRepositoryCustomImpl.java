package com.demo.security01.repository.category;

import com.demo.security01.entity.CategoryEntity;
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
    public List<CategoryEntity> findAll() {
        return (List<CategoryEntity>) queryFactory
                .selectFrom(categoryEntity)
                .where(categoryEntity.parentCategory.isNull())
                .fetch();
        /*public List<Category> findAll() {
            return em.createQuery("select c from Category c where c.parent is NULL", Category.class).getResultList();
        }*/
    }
}
