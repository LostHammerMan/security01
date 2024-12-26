package com.demo.security01.entity.tag;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudySkillTagEntity is a Querydsl query type for StudySkillTagEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudySkillTagEntity extends EntityPathBase<StudySkillTagEntity> {

    private static final long serialVersionUID = -1258413691L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudySkillTagEntity studySkillTagEntity = new QStudySkillTagEntity("studySkillTagEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSkillTagEntity skillTag;

    public final com.demo.security01.entity.study.QStudyEntity study;

    public QStudySkillTagEntity(String variable) {
        this(StudySkillTagEntity.class, forVariable(variable), INITS);
    }

    public QStudySkillTagEntity(Path<? extends StudySkillTagEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudySkillTagEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudySkillTagEntity(PathMetadata metadata, PathInits inits) {
        this(StudySkillTagEntity.class, metadata, inits);
    }

    public QStudySkillTagEntity(Class<? extends StudySkillTagEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.skillTag = inits.isInitialized("skillTag") ? new QSkillTagEntity(forProperty("skillTag")) : null;
        this.study = inits.isInitialized("study") ? new com.demo.security01.entity.study.QStudyEntity(forProperty("study"), inits.get("study")) : null;
    }

}

