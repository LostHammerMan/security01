package com.demo.security01.repository.comment;

import com.demo.security01.entity.comment.CommentEntity;

import java.util.List;

public interface CommentRepositoryCustom {

    List<CommentEntity> getCommentList();
}
