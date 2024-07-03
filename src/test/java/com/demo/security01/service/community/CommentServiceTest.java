package com.demo.security01.service.community;

import com.demo.security01.model.dto.comment.request.CommentModifyRequestDto;
import com.demo.security01.model.dto.comment.request.CommentRequestDto;
import com.demo.security01.model.dto.comment.response.CommentResponseDto;
import com.demo.security01.model.dto.comment.response.ModifyCommentResponseDto;
import com.demo.security01.repository.comment.CommentRepository;
import com.demo.security01.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Slf4j
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

//    @BeforeEach
//    void clean(){
//        commentRepository.deleteAll();
//    }

    @Test
    @DisplayName("댓글 저장 성공")
    void test1() {

        CommentRequestDto request = CommentRequestDto.builder()

                .boardIdx(18L)
                .content("댓글111").build();


        // 댓글 저장
        CommentResponseDto result = commentService.addComment(request);
        Assertions.assertEquals("댓글 1111111", result.getContent());
    }

    @Test
    @DisplayName("라운지 게시글 번호 입력시 해당 글의 댓글 가져오기")
    void getCommentList(){
        //
        String comment = null;
        Long boardId = 18L;
        List<CommentResponseDto> list = commentService.getCommentList(boardId);

        for (CommentResponseDto comments : list){
            log.info(comments.getContent());

        }

    }

    @Test
    @DisplayName("해당 댓글 수정 성공")
    void testModifyComment(){

        Long commentId = 29L;
        String username = "admin";

        CommentModifyRequestDto request = CommentModifyRequestDto.builder()
                .id(commentId)
                .content("수정222").build();

//        ModifyCommentResponseDto response = commentService.modifyComment(request, username);
//        Assertions.assertEquals("수정222", response.getContent());
    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteComment(){
        Long commentId = 29L;
        String username = "admin";

        commentService.deleteComment(commentId, username);
    }
}