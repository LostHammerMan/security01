package com.demo.security01.service;

import com.demo.security01.entity.User;
import com.demo.security01.model.dto.paging.Criteria;
import com.demo.security01.model.dto.paging.Paging;
import com.demo.security01.repository.UserRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.BindException;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepositoryCustomImpl userRepository;

    // 페이징
    @Transactional
    public Paging pagination(Criteria cri){


        // 페이징 객체 생성
        Paging paging = new Paging();
        paging.setCri(cri);

        // 전체 회원수 조회
        int userListCnt = userRepository.userListCnt(cri);

        paging.setTotalCount(userListCnt);
        return paging;
    }

    // 전체 회원 조회
    @Transactional
    public List<User> allUser(Criteria cri){
        return userRepository.findAllUser(cri);
    }

    // 회원 조회
    @Transactional
    public User userDetails(User user){
        return userRepository.findUser(user.getId());
    }


}
