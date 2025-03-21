package com.demo.security01.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategoryEntity is a Querydsl query type for CategoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryEntity extends EntityPathBase<CategoryEntity> {

    private static final long serialVersionUID = -1715150051L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCategoryEntity categoryEntity = new QCategoryEntity("categoryEntity");

    public final NumberPath<Long> categoryIdx = createNumber("categoryIdx", Long.class);

    public final StringPath categoryName = createString("categoryName");

    public final StringPath categoryPath = createString("categoryPath");

    public final NumberPath<Integer> depth = createNumber("depth", Integer.class);

    public final QCategoryEntity parentCategory;

    public final ListPath<CategoryEntity, QCategoryEntity> subCategory = this.<CategoryEntity, QCategoryEntity>createList("subCategory", CategoryEntity.class, QCategoryEntity.class, PathInits.DIRECT2);

    public QCategoryEntity(String variable) {
        this(CategoryEntity.class, forVariable(variable), INITS);
    }

    public QCategoryEntity(Path<? extends CategoryEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCategoryEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCategoryEntity(PathMetadata metadata, PathInits inits) {
        this(CategoryEntity.class, metadata, inits);
    }

    public QCategoryEntity(Class<? extends CategoryEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parentCategory = inits.isInitialized("parentCategory") ? new QCategoryEntity(forProperty("parentCategory"), inits.get("parentCategory")) : null;
    }

}

