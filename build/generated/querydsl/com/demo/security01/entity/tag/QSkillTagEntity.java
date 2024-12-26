package com.demo.security01.entity.tag;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSkillTagEntity is a Querydsl query type for SkillTagEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSkillTagEntity extends EntityPathBase<SkillTagEntity> {

    private static final long serialVersionUID = -109431748L;

    public static final QSkillTagEntity skillTagEntity = new QSkillTagEntity("skillTagEntity");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath skillImgName = createString("skillImgName");

    public final ListPath<StudySkillTagEntity, QStudySkillTagEntity> studySkillTagEntity = this.<StudySkillTagEntity, QStudySkillTagEntity>createList("studySkillTagEntity", StudySkillTagEntity.class, QStudySkillTagEntity.class, PathInits.DIRECT2);

    public final StringPath tagName = createString("tagName");

    public QSkillTagEntity(String variable) {
        super(SkillTagEntity.class, forVariable(variable));
    }

    public QSkillTagEntity(Path<? extends SkillTagEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSkillTagEntity(PathMetadata metadata) {
        super(SkillTagEntity.class, metadata);
    }

}

