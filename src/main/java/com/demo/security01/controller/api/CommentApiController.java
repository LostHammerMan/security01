package com.demo.security01.controller.api;

import com.demo.security01.model.dto.comment.CommentRequestDto;
import com.demo.security01.model.dto.comment.CommentResponseDto;
import com.demo.security01.service.community.CommentService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/addComment")
    public ResponseEntity<?> addComment(@RequestBody CommentRequestDto request, Principal principal){
        log.info("=== addComment =====");
        if (principal.getName() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인 필요");
        }
        request.setUsername(principal.getName());
        log.info("commentRequest = {}", request);
        CommentResponseDto response = commentService.addComment(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
