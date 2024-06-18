package com.demo.security01.controller.api;

import com.demo.security01.config.annotation.CommentAnno;
import com.demo.security01.model.dto.comment.request.CommentRequestDto;
import com.demo.security01.model.dto.comment.response.CommentResponseDto;
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

    @PostMapping("/api/addComment")
    public ResponseEntity<?> addComment(@RequestBody @CommentAnno CommentRequestDto request, Principal principal){
        log.info("=== addComment =====");
        if (principal.getName() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인 필요");
        }
        request.setUsername(principal.getName());
        log.info("commentRequest = {}", request);
        CommentResponseDto response = commentService.addComment(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/api/getComments/{boardId}")
    public ResponseEntity<?> getComment(@PathVariable Long boardId){
        log.info("===== Ajax getComments =====");
        List<CommentResponseDto> commentList = commentService.getCommentList(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }
}
