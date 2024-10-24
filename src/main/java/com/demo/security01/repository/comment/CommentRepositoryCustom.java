package com.demo.security01.repository.comment;

import com.demo.security01.entity.comment.CommentEntity;
import com.demo.security01.model.BoardType;

import java.util.List;

public interface CommentRepositoryCustom {
    List<CommentEntity> getCommentList(Long boardId, BoardType boardType);
    Integer getCommentListCount(Long loungeIdx, BoardType boardType);
}


