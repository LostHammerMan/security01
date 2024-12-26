package com.demo.security01.entity.lounge;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardLike is a Querydsl query type for BoardLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardLike extends EntityPathBase<BoardLike> {

    private static final long serialVersionUID = 1397154117L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardLike boardLike = new QBoardLike("boardLike");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLoungeEntity lounge;

    public final com.demo.security01.entity.study.QStudyEntity study;

    public final com.demo.security01.entity.user.QUser user;

    public QBoardLike(String variable) {
        this(BoardLike.class, forVariable(variable), INITS);
    }

    public QBoardLike(Path<? extends BoardLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardLike(PathMetadata metadata, PathInits inits) {
        this(BoardLike.class, metadata, inits);
    }

    public QBoardLike(Class<? extends BoardLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lounge = inits.isInitialized("lounge") ? new QLoungeEntity(forProperty("lounge"), inits.get("lounge")) : null;
        this.study = inits.isInitialized("study") ? new com.demo.security01.entity.study.QStudyEntity(forProperty("study"), inits.get("study")) : null;
        this.user = inits.isInitialized("user") ? new com.demo.security01.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

