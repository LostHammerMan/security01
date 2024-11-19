package com.demo.security01.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1859766734L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final ListPath<com.demo.security01.entity.lounge.BoardLike, com.demo.security01.entity.lounge.QBoardLike> boardLikes = this.<com.demo.security01.entity.lounge.BoardLike, com.demo.security01.entity.lounge.QBoardLike>createList("boardLikes", com.demo.security01.entity.lounge.BoardLike.class, com.demo.security01.entity.lounge.QBoardLike.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> createDate = createDateTime("createDate", java.sql.Timestamp.class);

    public final StringPath email = createString("email");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath nickName = createString("nickName");

    public final StringPath password = createString("password");

    public final EnumPath<com.demo.security01.model.Role> role = createEnum("role", com.demo.security01.model.Role.class);

    public final QUserAddr userAddr;

    public final StringPath username = createString("username");

    public final QUserProfile userProfile;

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userAddr = inits.isInitialized("userAddr") ? new QUserAddr(forProperty("userAddr"), inits.get("userAddr")) : null;
        this.userProfile = inits.isInitialized("userProfile") ? new QUserProfile(forProperty("userProfile"), inits.get("userProfile")) : null;
    }

}

