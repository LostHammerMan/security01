package com.demo.security01.entity.tag;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser_Skilltag is a Querydsl query type for User_Skilltag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser_Skilltag extends EntityPathBase<User_Skilltag> {

    private static final long serialVersionUID = -95831987L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser_Skilltag user_Skilltag = new QUser_Skilltag("user_Skilltag");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final QSkillTagEntity skillTag;

    public final com.demo.security01.entity.user.QUser user;

    public QUser_Skilltag(String variable) {
        this(User_Skilltag.class, forVariable(variable), INITS);
    }

    public QUser_Skilltag(Path<? extends User_Skilltag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser_Skilltag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser_Skilltag(PathMetadata metadata, PathInits inits) {
        this(User_Skilltag.class, metadata, inits);
    }

    public QUser_Skilltag(Class<? extends User_Skilltag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.skillTag = inits.isInitialized("skillTag") ? new QSkillTagEntity(forProperty("skillTag")) : null;
        this.user = inits.isInitialized("user") ? new com.demo.security01.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

