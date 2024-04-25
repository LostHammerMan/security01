package com.demo.security01.service.community;

import com.demo.security01.config.exception.LoungeNotFountException;
import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.entity.lounge.BoardLike;
import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.repository.boardLike.LikeRepository;
import com.demo.security01.repository.community.LoungeRepository;
import com.demo.security01.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LikeService {

    private final LoungeRepository loungeRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    public void addLike(Long boardId, User user){
        LoungeEntity findLounge = loungeRepository.findById(boardId).orElseThrow(
                () -> new LoungeNotFountException()
        );
        if (!likeRepository.existsByUserAndBoard(user, findLounge)){
            findLounge.setLikeCount(findLounge.getLikeCount() + 1);
            likeRepository.save(new BoardLike(user, findLounge));
        }else {
            findLounge.setLikeCount(findLounge.getLikeCount() - 1);
            likeRepository.deleteByUserAndBoard(user, findLounge);
        }


    }
}
