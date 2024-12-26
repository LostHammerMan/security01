package com.demo.security01.entity.study;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyEntity is a Querydsl query type for StudyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudyEntity extends EntityPathBase<StudyEntity> {

    private static final long serialVersionUID = 694372427L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyEntity studyEntity = new QStudyEntity("studyEntity");

    public final ListPath<com.demo.security01.entity.lounge.BoardLike, com.demo.security01.entity.lounge.QBoardLike> boardLikes = this.<com.demo.security01.entity.lounge.BoardLike, com.demo.security01.entity.lounge.QBoardLike>createList("boardLikes", com.demo.security01.entity.lounge.BoardLike.class, com.demo.security01.entity.lounge.QBoardLike.class, PathInits.DIRECT2);

    public final com.demo.security01.entity.QCategoryEntity category;

    public final ListPath<com.demo.security01.entity.comment.CommentEntity, com.demo.security01.entity.comment.QCommentEntity> commentEntities = this.<com.demo.security01.entity.comment.CommentEntity, com.demo.security01.entity.comment.QCommentEntity>createList("commentEntities", com.demo.security01.entity.comment.CommentEntity.class, com.demo.security01.entity.comment.QCommentEntity.class, PathInits.DIRECT2);

    public final StringPath contactAddress = createString("contactAddress");

    public final StringPath contactMethod = createString("contactMethod");

    public final StringPath contents = createString("contents");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final NumberPath<Integer> isFIn = createNumber("isFIn", Integer.class);

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final StringPath progressMethod = createString("progressMethod");

    public final StringPath progressPeriod = createString("progressPeriod");

    public final DatePath<java.time.LocalDate> recruitDeadline = createDate("recruitDeadline", java.time.LocalDate.class);

    public final NumberPath<Integer> recruitedNumber = createNumber("recruitedNumber", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final SetPath<Study_Positions, QStudy_Positions> study_positions = this.<Study_Positions, QStudy_Positions>createSet("study_positions", Study_Positions.class, QStudy_Positions.class, PathInits.DIRECT2);

    public final ListPath<com.demo.security01.entity.tag.StudySkillTagEntity, com.demo.security01.entity.tag.QStudySkillTagEntity> studySkillTagEntity = this.<com.demo.security01.entity.tag.StudySkillTagEntity, com.demo.security01.entity.tag.QStudySkillTagEntity>createList("studySkillTagEntity", com.demo.security01.entity.tag.StudySkillTagEntity.class, com.demo.security01.entity.tag.QStudySkillTagEntity.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final com.demo.security01.entity.user.QUser user;

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public QStudyEntity(String variable) {
        this(StudyEntity.class, forVariable(variable), INITS);
    }

    public QStudyEntity(Path<? extends StudyEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyEntity(PathMetadata metadata, PathInits inits) {
        this(StudyEntity.class, metadata, inits);
    }

    public QStudyEntity(Class<? extends StudyEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new com.demo.security01.entity.QCategoryEntity(forProperty("category"), inits.get("category")) : null;
        this.user = inits.isInitialized("user") ? new com.demo.security01.entity.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

