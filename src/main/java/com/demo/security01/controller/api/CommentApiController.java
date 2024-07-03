package com.demo.security01.controller.api;

import com.demo.security01.config.annotation.CommentAnno;
import com.demo.security01.config.exception.UserNotMatchException;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.comment.request.CommentModifyRequestDto;
import com.demo.security01.model.dto.comment.request.CommentRequestDto;
import com.demo.security01.model.dto.comment.response.CommentResponseDto;
import com.demo.security01.model.dto.comment.response.ModifyCommentResponseDto;
import com.demo.security01.service.community.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;


    // 댓글 작성
    @PostMapping("/api/addComment")
    public ResponseEntity<?> addComment(@RequestBody CommentRequestDto request, Principal principal){
        log.info("=== addComment =====");
//        if (principal.getName() == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인 필요");
//        }
        request.setUsername(principal.getName());
        log.info("commentRequest = {}", request);
        CommentResponseDto response = commentService.addComment(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 댓글 수정
    @PutMapping("/api/comment/{commentId}")
    public ResponseEntity<?> modifyComment(@PathVariable Long commentId, @RequestBody CommentModifyRequestDto request, Principal principal){
        String loginUsername = principal.getName();

        ModifyCommentResponseDto responseDto = commentService.modifyComment(commentId, request, loginUsername);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // 댓글 하나 조회


    // 댓글 리스트 가져오기
    @GetMapping("/api/getComments/{boardId}")
    public ResponseEntity<?> getComment(@PathVariable Long boardId){
        log.info("===== Ajax getComments =====");
        List<CommentResponseDto> commentList = commentService.getCommentList(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }

    // 한 게시글 당 댓글 갯수
    @GetMapping("/api/getCommentsCount/{boardId}")
    public ResponseEntity<?> getCommentsListCount(@PathVariable Long boardId){
        log.info("=== api - getCommentsListCount ===");
        Integer commentCount = commentService.getCommentCounts(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(commentCount);
    }
}
