package com.demo.security01.repository.boardLike;

import com.demo.security01.entity.lounge.BoardLike;
import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<BoardLike, Long>, LikeRepositoryCustom {

    boolean existsByUserAndBoard(User user, LoungeEntity lounge);
    void deleteByUserAndBoard(User user, LoungeEntity lounge);
}
