package com.demo.security01.entity.study;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecruitPositions is a Querydsl query type for RecruitPositions
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecruitPositions extends EntityPathBase<RecruitPositions> {

    private static final long serialVersionUID = 1957508333L;

    public static final QRecruitPositions recruitPositions = new QRecruitPositions("recruitPositions");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath positionName = createString("positionName");

    public QRecruitPositions(String variable) {
        super(RecruitPositions.class, forVariable(variable));
    }

    public QRecruitPositions(Path<? extends RecruitPositions> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecruitPositions(PathMetadata metadata) {
        super(RecruitPositions.class, metadata);
    }

}

