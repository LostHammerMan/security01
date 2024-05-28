package com.demo.security01.controller.api;

import com.demo.security01.entity.lounge.BoardLike;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.boardLike.BoardLikeRequestDto;
import com.demo.security01.model.dto.reponseDto.ResponseEntityDto;
import com.demo.security01.service.community.LikeService;
import com.demo.security01.service.community.LoungeService;
import com.demo.security01.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LikeApiController {

    private final UserService userService;
    private final LoungeService loungeService;
    private final LikeService likeService;

    // 좋아요 추가
    @PostMapping("/api/addLike")
    public ResponseEntity<Object> addLike(
            @RequestBody BoardLikeRequestDto dto,
            Principal principal){
        log.info("== addLike called .. == ");

        if(principal == null){
            throw new RuntimeException("로그인이 필요한 기능임");
        }
        String username = principal.getName();


        if (username == null){
            throw new RuntimeException("로그인이 필요한 기능입니다.");
        }

        if (dto.getBoardId() == null){
            throw new RuntimeException("게시글이 존재하지 않음");
        }

        likeService.addLike(dto.getBoardId(), username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 좋아요 수 불러오기
    @GetMapping("/api/addLike/{boardId}")
    public ResponseEntity<Object> getLikeCount(@PathVariable Long boardId){
        log.info("getLikeCount() called...............");
        int likeCount = likeService.getLikeCount(boardId);
        ResponseEntityDto response = ResponseEntityDto.builder()
                .status(HttpStatus.OK.value())
                .objectData(likeCount)
                .localDateTime(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/api/deleteLike")
    public ResponseEntity<Object> deleteLike(@RequestBody BoardLikeRequestDto dto,
                                             Principal principal){
        log.info("=== deleteLike called ... =====");
        String username = principal.getName();

        if (username == null){
            throw new RuntimeException("로그인이 필요한 기능입니다.");
        }

        if (dto.getBoardId() == null){
            throw new RuntimeException("게시글이 존재하지 않음");
        }

        likeService.deleteLike(dto.getBoardId(), username);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
