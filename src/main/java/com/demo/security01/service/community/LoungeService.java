package com.demo.security01.service.community;

import com.demo.security01.config.exception.LoungeNotFountException;
import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.dto.community.LoungeModifyRequest;
import com.demo.security01.model.dto.community.LoungeWriteRequest;
import com.demo.security01.repository.community.LoungeRepository;
import com.demo.security01.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoungeService {

    private final LoungeRepository loungeRepository;
    private final UserRepository userRepository;

    // 게시글 저장
    @Transactional
    public void loungeSave(LoungeWriteRequest request, String username){

        User findUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저 정보를 찾을 수 없습니다"));

        LoungeEntity entity = LoungeEntity.builder()
                .title(request.getTitle())
                .content(request.getContents())
                .cateCode(request.getCateCode())
                .regDate(LocalDateTime.now())
                .user(findUser)
                .build();

        loungeRepository.save(entity);

    }

    // 라운지 수정
    @Transactional
    public void loungeModify(LoungeModifyRequest request, Long loungeId){

        LoungeEntity findLounge = loungeRepository.findById(loungeId)
                .orElseThrow(()-> new LoungeNotFountException());

        findLounge.loungeEdit(request);

    }

    // 라운지 삭제
    @Transactional
    public void loungeDelete(Long loungeId){

        LoungeEntity findLounge = loungeRepository.findById(loungeId)
                .orElseThrow(()-> new LoungeNotFountException());

        loungeRepository.deleteById(findLounge.getIdx());

    }

    // 라운지 목록
    public List<LoungeEntity> findAllLounge(){
        return loungeRepository.findAll();
    }
}
