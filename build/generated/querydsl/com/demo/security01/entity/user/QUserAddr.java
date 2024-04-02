package com.demo.security01.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAddr is a Querydsl query type for UserAddr
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAddr extends EntityPathBase<UserAddr> {

    private static final long serialVersionUID = -1980048061L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserAddr userAddr = new QUserAddr("userAddr");

    public final NumberPath<Long> addrIdx = createNumber("addrIdx", Long.class);

    public final StringPath postAddr1 = createString("postAddr1");

    public final StringPath postAddr2 = createString("postAddr2");

    public final QUser user;

    public final StringPath writer = createString("writer");

    public final StringPath zipCode = createString("zipCode");

    public QUserAddr(String variable) {
        this(UserAddr.class, forVariable(variable), INITS);
    }

    public QUserAddr(Path<? extends UserAddr> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserAddr(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserAddr(PathMetadata metadata, PathInits inits) {
        this(UserAddr.class, metadata, inits);
    }

    public QUserAddr(Class<? extends UserAddr> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

