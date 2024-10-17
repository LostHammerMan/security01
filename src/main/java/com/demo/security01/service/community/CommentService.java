package com.demo.security01.service.community;

import com.demo.security01.config.exception.CommentNotFoundException;
import com.demo.security01.config.exception.CommentUserNotMatchException;
import com.demo.security01.config.exception.LoungeNotFountException;
import com.demo.security01.entity.comment.CommentEntity;
import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.study.StudyEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.BoardType;
import com.demo.security01.model.dto.comment.request.CommentModifyRequestDto;
import com.demo.security01.model.dto.comment.request.CommentRequestDto;
import com.demo.security01.model.dto.comment.response.CommentResponseDto;
import com.demo.security01.model.dto.comment.response.ModifyCommentResponseDto;
import com.demo.security01.repository.comment.CommentRepository;
import com.demo.security01.repository.community.LoungeRepository;
import com.demo.security01.repository.study.StudyRepository;
import com.demo.security01.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final LoungeRepository loungeRepository;
    private final StudyRepository studyRepository;

    @Transactional
    public CommentResponseDto addComment(CommentRequestDto request){

        User findUser = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저는 존재하지 않음"));

        if(request.getBoardType() == BoardType.LOUNGE) {
        	LoungeEntity findLounge = loungeRepository.findById(request.getBoardIdx())
                    .orElseThrow(() -> new LoungeNotFountException());

            CommentEntity addedComment = CommentEntity.builder()
                    .content(request.getContent())
                    .lounge(findLounge)
                    .user(findUser)
                    .build();

            CommentEntity comment = commentRepository.save(addedComment);

            CommentResponseDto response = CommentResponseDto.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .username(findUser.getUsername())
                    .filePath(findUser.getUserProfile().getFileName())
                    .regDate(comment.getRegDate()).build();
            return response;
        }else if(request.getBoardType() == BoardType.STUDY) {
        	
        	StudyEntity findStudy = studyRepository.findById(request.getBoardIdx())
        			.orElseThrow(
        					() -> new EntityNotFoundException("해당 스터디/프로젝트는 존재하지 않음"));

            CommentEntity addedComment = CommentEntity.builder()
                    .content(request.getContent())
                    .study(findStudy)
                    .user(findUser)
                    .build();

            CommentEntity comment = commentRepository.save(addedComment);

            CommentResponseDto response = CommentResponseDto.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .username(findUser.getUsername())
                    .filePath(findUser.getUserProfile().getFileName())
                    .regDate(comment.getRegDate()).build();
            return response;
        }
		throw new IllegalArgumentException("해당 게시판은 존재하지 않습니다.");
        
    }

    // 전체 댓글 가져오기 + 페이징 X
//    public List<CommentEntity> getCommentList(Long boardId){
//        List<CommentEntity> commentList = commentRepository.getCommentList(boardId);
//        return commentList;
//    }

    // 전체 댓글 + dto
    public List<CommentResponseDto> getCommentList(Long boardId){
        List<CommentEntity> commentList = commentRepository.getCommentList(boardId);
        List<CommentResponseDto> responseDtoList = new ArrayList<>();
        for (CommentEntity comment : commentList){
            CommentResponseDto response = CommentResponseDto.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .username(comment.getUser().getUsername())
                    .filePath(comment.getUser().getUserProfile().getFileName())
                    .regDate(comment.getRegDate())
                    .build();

            responseDtoList.add(response);
        }


        return responseDtoList;
    }

    // 댓글 수정
    @Transactional
    public ModifyCommentResponseDto modifyComment(Long commentId ,CommentModifyRequestDto request, String loginUsername){

        CommentEntity findComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException());

        String commentUsername = findComment.getUser().getUsername();

        if (!commentUsername.equals(loginUsername)){
            throw new CommentUserNotMatchException();
        }

        findComment.modifyComment(request);

        ModifyCommentResponseDto response = ModifyCommentResponseDto.builder()
                .id(findComment.getId())
                .content(findComment.getContent())
                .build();

        return response;
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId, String username){

        CommentEntity findComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException());

        String commentUsername = findComment.getUser().getUsername();

        if (!commentUsername.equals(username)){
            throw new CommentUserNotMatchException();
        }
        commentRepository.deleteById(findComment.getId());
    }

    // 댓글 수 카운트
    @Transactional
    public int getCommentCounts(Long loungeIdx){
        return commentRepository.getCommentListCount(loungeIdx);
    }


}
