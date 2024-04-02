package com.demo.security01.entity.lounge;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLoungeEntity is a Querydsl query type for LoungeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLoungeEntity extends EntityPathBase<LoungeEntity> {

    private static final long serialVersionUID = 1004533525L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLoungeEntity loungeEntity = new QLoungeEntity("loungeEntity");

    public final com.demo.security01.entity.QCategoryEntity cateCode;

    public final StringPath content = createString("content");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final com.demo.security01.entity.user.QUser user;

    public QLoungeEntity(String variable) {
        this(LoungeEntity.class, forVariable(variable), INITS);
    }

    public QLoungeEntity(Path<? extends LoungeEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLoungeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLoungeEntity(PathMetadata metadata, PathInits inits) {
        this(LoungeEntity.class, metadata, inits);
    }

    public QLoungeEntity(Class<? extends LoungeEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cateCode = inits.isInitialized("cateCode") ? new com.demo.security01.entity.QCategoryEntity(forProperty("cateCode"), inits.get("cateCode")) : null;
        this.user = inits.isInitialized("user") ? new com.demo.security01.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

