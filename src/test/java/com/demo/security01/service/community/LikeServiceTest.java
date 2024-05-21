package com.demo.security01.service.community;

import com.demo.security01.config.exception.LoungeNotFountException;
import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.repository.community.LoungeRepository;
import com.demo.security01.repository.user.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class LikeServiceTest {

    @Autowired
    private LikeService likeService;
    @Autowired
    private LoungeRepository loungeRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void addLike(){

        LoungeEntity findLounge = loungeRepository.findById(18L).orElseThrow(() -> new LoungeNotFountException());
        User findUser = userRepository.findByUsername("admin3").orElseThrow(() -> new UsernameNotFoundException("해당 유저 없음"));

//        likeService.addLike(findLounge.getIdx(), findUser);

    }

}