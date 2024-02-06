package com.demo.security01.repository.community;

import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoungeRepository extends JpaRepository<LoungeEntity, Long>/*, LoungeRepositoryCustom */{

//    User findByUsername(String username);
}
