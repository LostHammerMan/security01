package com.demo.security01.repository.boardLike;

import com.demo.security01.entity.lounge.BoardLike;
import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<BoardLike, Long>, LikeRepositoryCustom {

    boolean existsByUserAndLounge(User user, LoungeEntity lounge);
    void deleteByUserAndLounge(User user, LoungeEntity lounge);
}
