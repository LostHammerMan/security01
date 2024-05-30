package com.demo.security01.repository.comment;

import com.demo.security01.entity.comment.CommentEntity;
import com.demo.security01.entity.comment.QCommentEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.demo.security01.entity.comment.QCommentEntity.*;

@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentEntity> getCommentList(Long boardId) {
        return queryFactory.selectFrom(commentEntity)
                .where(commentEntity.lounge.idx.eq(boardId))
                .orderBy(commentEntity.id.asc())
                .fetch();

    }
}
