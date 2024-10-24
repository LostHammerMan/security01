package com.demo.security01.controller.api;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.security01.model.BoardType;
import com.demo.security01.model.dto.comment.request.CommentModifyRequestDto;
import com.demo.security01.model.dto.comment.request.CommentRequestDto;
import com.demo.security01.model.dto.comment.response.CommentResponseDto;
import com.demo.security01.model.dto.comment.response.ModifyCommentResponseDto;
import com.demo.security01.model.dto.reponseDto.ResponseDto;
import com.demo.security01.service.community.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;
//    private final CommentModifyValidator commentModifyValidator;

    @Resource(name = "commentModifyValidator")
    private Validator commentModifyValidator;

    @Resource(name = "commentWriteValidator")
    private Validator commentWriteValidator;

    @InitBinder("commentRequestDto")
    public void initCommentWriteBinder(WebDataBinder binder){
        binder.addValidators(commentWriteValidator);
    }

    @InitBinder("commentModifyRequestDto")
    public void initCommentModifyBinder(WebDataBinder binder){
        binder.addValidators(commentModifyValidator);
    }

    // 댓글 작성
    @PostMapping("/api/comment/add")
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentRequestDto commentRequestDto, BindingResult result, Principal principal){
        log.info("=== addComment =====");
        commentRequestDto.setUsername(principal.getName());

        if (result.hasErrors()){
            log.info("댓글 작성 중 에러 발생!!!");
            log.info("error = {}", result.getAllErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        log.info("commentRequest = {}", commentRequestDto);
        CommentResponseDto response = commentService.addComment(commentRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 댓글 수정
    @PutMapping("/api/comment/{commentId}")
    public ResponseEntity<?> modifyComment(@PathVariable Long commentId, @Valid @RequestBody CommentModifyRequestDto commentModifyRequestDto, BindingResult result, Principal principal){
        log.info("====== modifyComment ============");
        String loginUsername = principal.getName();

        log.info("request.getContent = {}", commentModifyRequestDto.getContent());

        if (result.hasErrors()){
            log.info("\t\t댓글 수정중 오류 발생 !!!!");
            log.info("result = {}", result.getAllErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }
        ModifyCommentResponseDto responseDto = commentService.modifyComment(commentId, commentModifyRequestDto, loginUsername);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // 댓글 삭제
    @DeleteMapping("/api/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId, Principal principal){
        String loginUsername = principal.getName();
        commentService.deleteComment(commentId, loginUsername);

        ResponseDto response = ResponseDto.builder()
                .status(HttpStatus.OK.value())
                .message("댓글 삭제 완료")
                .timeStamp(LocalDateTime.now()).build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    // 댓글 리스트 가져오기
    @GetMapping("/api/getComments/{boardType}/{boardId}")
    public ResponseEntity<?> getComment(@PathVariable(name="boardType") BoardType boardType, @PathVariable(name="boardId") Long boardId){
        log.info("===== Ajax getComments =====");
        
       
        List<CommentResponseDto> commentList = commentService.getCommentList(boardId, boardType);
        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }

    // 한 게시글 당 댓글 갯수
    @GetMapping("/api/getCommentsCount/{boardType}/{boardId}")
    public ResponseEntity<?> getCommentsListCount(@PathVariable(name = "boardType") BoardType boardType ,@PathVariable(name="boardId") Long boardId){
        log.info("=== api - getCommentsListCount ===");
        Integer commentCount = commentService.getCommentCounts(boardId, boardType);
        return ResponseEntity.status(HttpStatus.OK).body(commentCount);
    }
}
