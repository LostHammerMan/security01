package com.demo.security01.service.community;

import com.demo.security01.entity.comment.CommentEntity;
import com.demo.security01.model.dto.comment.CommentRequestDto;
import com.demo.security01.model.dto.comment.CommentResponseDto;
import com.demo.security01.repository.comment.CommentRepository;
import com.demo.security01.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void clean(){
        commentRepository.deleteAll();
    }

    @Test
    @DisplayName("댓글 저장 성공")
    void test1() {

        CommentRequestDto request = CommentRequestDto.builder()
                .boardIdx(18L)
                .content("댓글 1111111").build();


        // 댓글 저장
        CommentResponseDto result = commentService.addComment(request);
        Assertions.assertEquals("댓글 1111111", result.getContent());
    }
}