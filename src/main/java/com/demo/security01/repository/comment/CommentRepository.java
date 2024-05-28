package com.demo.security01.repository.comment;

import com.demo.security01.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>, CommentRepositoryCustom {
}
