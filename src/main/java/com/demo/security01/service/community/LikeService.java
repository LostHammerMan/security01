package com.demo.security01.service.community;

import com.demo.security01.config.exception.LoungeNotFountException;
import com.demo.security01.entity.CategoryEntity;
import com.demo.security01.entity.lounge.BoardLike;
import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.repository.boardLike.LikeRepository;
import com.demo.security01.repository.community.LoungeRepository;
import com.demo.security01.repository.user.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public void addLike(Long boardId, String username){
        LoungeEntity findLounge = loungeRepository.findById(boardId).orElseThrow(
                () -> new LoungeNotFountException()
        );

        User findUser = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("해당 유저 없음")
        );
        if (!likeRepository.existsByUserAndLounge(findUser, findLounge)){
            findLounge.setLikeCount(findLounge.getLikeCount() + 1);
            likeRepository.save(new BoardLike(findUser, findLounge));
        }/*else {*/
//            findLounge.setLikeCount(findLounge.getLikeCount() - 1);
//            likeRepository.deleteByUserAndLounge(user, findLounge);
//        }
    }

    public void deleteLike(Long boardId, String username){
        LoungeEntity findLounge = loungeRepository.findById(boardId).orElseThrow(
                () -> new LoungeNotFountException()
        );

        User findUser = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("해당 유저 없음")
        );
        if (likeRepository.existsByUserAndLounge(findUser, findLounge)){
            findLounge.setLikeCount(findLounge.getLikeCount() - 1);
            likeRepository.deleteByUserAndLounge(findUser, findLounge);
        }
    }

    public int getLikeCount(Long boardId) {
        LoungeEntity findLounge = loungeRepository.findById(boardId).orElseThrow(
                () -> new LoungeNotFountException()
        );

        int likeCount = findLounge.getLikeCount();
        return likeCount;
    }
}
