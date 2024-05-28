package com.demo.security01.service.community;

import com.demo.security01.config.exception.LoungeNotFountException;
import com.demo.security01.entity.comment.CommentEntity;
import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.comment.CommentRequestDto;
import com.demo.security01.model.dto.comment.CommentResponseDto;
import com.demo.security01.repository.comment.CommentRepository;
import com.demo.security01.repository.community.LoungeRepository;
import com.demo.security01.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final LoungeRepository loungeRepository;

    public CommentResponseDto addComment(CommentRequestDto request){

        User findUser = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저는 존재하지 않음"));

        LoungeEntity findLounge = loungeRepository.findById(request.getBoardIdx())
                .orElseThrow(() -> new LoungeNotFountException());

        CommentEntity addedComment = CommentEntity.builder()
                .content(request.getContent())
                .lounge(findLounge)
                .user(findUser)
                .build();

        CommentEntity comment = commentRepository.save(addedComment);

        CommentResponseDto response = CommentResponseDto.builder()
                .content(comment.getContent())
                .regDate(comment.getRegDate()).build();

        return response;
    }

//    public List<CommentEntity> getCommentList(){
//
//
//        return
//    }
}
