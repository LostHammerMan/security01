package com.demo.security01.controller.api;

import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.entity.lounge.BoardLike;
import com.demo.security01.service.community.LoungeService;
import com.demo.security01.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class LikeApiController {

    private final UserService userService;
    private final LoungeService loungeService;

    // 좋아요 추가
//    @PostMapping("/api/addLike/up/{cateCode}/{boardId}")
//    public ResponseEntity<BoardLike> addLike(@PathVariable CategoryEntity cateCode,
//                                             @PathVariable Long boardId,
//                                             Principal principal){
//
//        String username = principal.getName();
////        likeService.addLike(cateCode, boardId, username);
//
//    }

}
