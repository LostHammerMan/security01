package com.demo.security01.entity.study;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudy_Positions is a Querydsl query type for Study_Positions
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudy_Positions extends EntityPathBase<Study_Positions> {

    private static final long serialVersionUID = 550419219L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudy_Positions study_Positions = new QStudy_Positions("study_Positions");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final QRecruitPositions positions;

    public final QStudyEntity studyEntity;

    public QStudy_Positions(String variable) {
        this(Study_Positions.class, forVariable(variable), INITS);
    }

    public QStudy_Positions(Path<? extends Study_Positions> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudy_Positions(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudy_Positions(PathMetadata metadata, PathInits inits) {
        this(Study_Positions.class, metadata, inits);
    }

    public QStudy_Positions(Class<? extends Study_Positions> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.positions = inits.isInitialized("positions") ? new QRecruitPositions(forProperty("positions")) : null;
        this.studyEntity = inits.isInitialized("studyEntity") ? new QStudyEntity(forProperty("studyEntity"), inits.get("studyEntity")) : null;
    }

}

