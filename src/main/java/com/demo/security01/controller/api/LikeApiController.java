package com.demo.security01.controller.api;

import com.demo.security01.entity.lounge.BoardLike;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.boardLike.BoardLikeRequestDto;
import com.demo.security01.service.community.LikeService;
import com.demo.security01.service.community.LoungeService;
import com.demo.security01.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeApiController {

    private final UserService userService;
    private final LoungeService loungeService;
    private final LikeService likeService;

    // 좋아요 추가
    @PostMapping("/api/addLike")
    public ResponseEntity<Object> addLike(
            @RequestBody BoardLikeRequestDto dto,
            Authentication authentication){

        User user = (User)authentication.getPrincipal();

        if (user == null){
            throw new RuntimeException("로그인이 필요한 기능입니다.");
        }

        if (dto.getBoardId() == null){
            throw new RuntimeException("게시글이 존재하지 않음");
        }

        likeService.addLike(dto.getBoardId(), user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/addLike")
    public ResponseEntity<Object> deleteLike(@RequestBody BoardLikeRequestDto dto,
                                             Authentication authentication){
        User user = (User) authentication.getPrincipal();

        if (user == null){
            throw new RuntimeException("로그인이 필요한 기능입니다.");
        }

        if (dto.getBoardId() == null){
            throw new RuntimeException("게시글이 존재하지 않음");
        }

        likeService.deleteLike(dto.getBoardId(), user);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
