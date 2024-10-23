package com.demo.security01.repository.comment;

import com.demo.security01.entity.comment.CommentEntity;
import com.demo.security01.entity.comment.QCommentEntity;
import com.demo.security01.entity.lounge.QLoungeEntity;
import com.demo.security01.model.BoardType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.demo.security01.entity.comment.QCommentEntity.*;

@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    // 댓글 목록  todo 댓글 목록 페이징 추가 필요
    @Override
    public List<CommentEntity> getCommentList(Long boardId, BoardType boardType) {
    	BooleanExpression condition = null;
    	
    	if(boardType == BoardType.LOUNGE) {
    		condition = commentEntity.lounge.idx.eq(boardId);
    	}else if(boardType == BoardType.STUDY) {
    		condition = commentEntity.study.idx.eq(boardId);
    	}
    	
        return queryFactory.selectFrom(commentEntity)
                .where(condition)
                .orderBy(commentEntity.id.asc())
                .fetch();
    }

    @Override
    public Integer getCommentListCount(Long loungeIdx) {
        return Math.toIntExact(queryFactory
                .select(commentEntity.count())
                .from(commentEntity)
//                        .join(commentEntity.lounge, QLoungeEntity.loungeEntity)
                .where(commentEntity.lounge.idx.eq(loungeIdx))
//                .where(QLoungeEntity.loungeEntity.idx.eq(loungeIdx))
                .fetchFirst());
    }
}
